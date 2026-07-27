package com.airecruitment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airecruitment.dto.RegisterRequest;
import com.airecruitment.entity.Company;
import com.airecruitment.entity.User;
import com.airecruitment.repository.CompanyRepository;
import com.airecruitment.repository.UserRepository;
import com.airecruitment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public User registerUser(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        // Recruiter Registration
        if ("RECRUITER".equalsIgnoreCase(request.getRole())) {

            Company company = companyRepository
                    .findByCompanyName(request.getCompanyName())
                    .orElse(null);

            if (company == null) {

                company = new Company();

                company.setCompanyName(request.getCompanyName());
                company.setWebsite(request.getWebsite());
                company.setIndustry(request.getIndustry());
                company.setLocation(request.getLocation());
                company.setEmail(request.getCompanyEmail());

                company = companyRepository.save(company);
            }

            user.setCompany(company);
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {

            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setCompany(user.getCompany());

            return userRepository.save(existingUser);
        }

        return null;
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public User login(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }

        return null;
    }
}
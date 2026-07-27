package com.airecruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airecruitment.entity.Company;
import com.airecruitment.repository.CompanyRepository;
import com.airecruitment.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(Long id, Company company) {

        Company existingCompany = companyRepository.findById(id).orElse(null);

        if (existingCompany != null) {

            existingCompany.setCompanyName(company.getCompanyName());
            existingCompany.setWebsite(company.getWebsite());
            existingCompany.setIndustry(company.getIndustry());
            existingCompany.setLocation(company.getLocation());
            existingCompany.setEmail(company.getEmail());
            existingCompany.setAbout(company.getAbout());
            existingCompany.setLogo(company.getLogo());

            return companyRepository.save(existingCompany);
        }

        return null;
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
package com.airecruitment.service;

import java.util.List;

import com.airecruitment.dto.RegisterRequest;
import com.airecruitment.entity.User;

public interface UserService {

    User registerUser(RegisterRequest request);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User login(String email, String password);
}
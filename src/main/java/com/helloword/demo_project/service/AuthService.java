package com.helloword.demo_project.service;



import org.springframework.stereotype.Service;
import com.helloword.demo_project.model.User;
import com.helloword.demo_project.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    // Konstruktor
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Tanpa hashing password
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user); // ID akan dihasilkan otomatis
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}

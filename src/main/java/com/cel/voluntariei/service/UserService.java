package com.cel.voluntariei.service;

import com.cel.voluntariei.model.User;
import com.cel.voluntariei.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmailAndSenha(String email, String senha) {
        logger.debug("Trying to find User with email: {} and senha: {}", email, senha);
        Optional<User> user = userRepository.findByEmailAndSenha(email, senha);
        if (user.isPresent()) {
            logger.debug("User found: {}", user.get());
        } else {
            logger.debug("No User found with email: {} and senha: {}", email, senha);
        }
        return user;
    }
}
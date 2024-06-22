package com.cel.voluntariei.service;

import com.cel.voluntariei.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    Optional<User> findByEmailAndSenha(String email, String senha);
}

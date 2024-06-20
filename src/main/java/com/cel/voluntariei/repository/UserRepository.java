package com.cel.voluntariei.repository;

import com.cel.voluntariei.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndSenha(String nome, String senha);
}
package com.cel.voluntariei.repository;

import com.cel.voluntariei.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
package com.cel.voluntariei.repository;

import com.cel.voluntariei.model.Help;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRepository extends JpaRepository<Help, Long> {
}
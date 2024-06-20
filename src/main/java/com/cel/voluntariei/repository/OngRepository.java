package com.cel.voluntariei.repository;

import com.cel.voluntariei.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OngRepository extends JpaRepository<Ong, Long> {
}
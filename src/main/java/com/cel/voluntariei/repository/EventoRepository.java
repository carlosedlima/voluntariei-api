package com.cel.voluntariei.repository;

import com.cel.voluntariei.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
package com.cel.voluntariei.repository;

import com.cel.voluntariei.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong, Long> {
    Optional<Ong> findByEmailAndSenha(String nome, String senha);
}

package com.cel.voluntariei.service;

import com.cel.voluntariei.model.Ong;

import java.util.List;
import java.util.Optional;

public interface IOngService {

    List<Ong> getAllOngs();
    Optional<Ong> getOngById(Long id);
    Ong saveOng(Ong ong);
    void deleteOng(Long id);
    Optional<Ong> findByEmailAndSenha(String email, String senha);
}

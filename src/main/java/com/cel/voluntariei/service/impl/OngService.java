package com.cel.voluntariei.service.impl;

import com.cel.voluntariei.model.Ong;
import com.cel.voluntariei.repository.OngRepository;
import com.cel.voluntariei.service.IOngService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OngService implements IOngService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private OngRepository ongRepository;

    public List<Ong> getAllOngs() {
        return ongRepository.findAll();
    }

    public Optional<Ong> getOngById(Long id) {
        return ongRepository.findById(id);
    }

    public Ong saveOng(Ong ong) {
        return ongRepository.save(ong);
    }

    public void deleteOng(Long id) {
        ongRepository.deleteById(id);
    }

    public Optional<Ong> findByEmailAndSenha(String email, String senha) {
        Optional<Ong> ong = ongRepository.findByEmailAndSenha(email, senha);
        if (ong.isPresent()) {
            logger.debug("Ong found: {}", ong.get());
        } else {
            logger.debug("No Ong found with email: {} and senha: {}", email, senha);
        }
        return ong;
    }
}
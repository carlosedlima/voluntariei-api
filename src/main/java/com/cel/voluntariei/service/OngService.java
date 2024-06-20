package com.cel.voluntariei.service;

import com.cel.voluntariei.model.Ong;
import com.cel.voluntariei.repository.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OngService {
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
}
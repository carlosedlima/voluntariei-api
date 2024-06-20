package com.cel.voluntariei.service;

import com.cel.voluntariei.model.Help;
import com.cel.voluntariei.repository.HelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpService {
    @Autowired
    private HelpRepository helpRepository;

    public List<Help> getAllHelps() {
        return helpRepository.findAll();
    }

    public Optional<Help> getHelpById(Long id) {
        return helpRepository.findById(id);
    }

    public Help saveHelp(Help help) {
        return helpRepository.save(help);
    }

    public void deleteHelp(Long id) {
        helpRepository.deleteById(id);
    }
}
package com.cel.voluntariei.service;

import com.cel.voluntariei.model.Help;

import java.util.List;
import java.util.Optional;

public interface IHelpService {
    List<Help> getAllHelps();
    Optional<Help> getHelpById (Long id);
    Help saveHelp(Help help);
    void deleteHelp(Long id);
}

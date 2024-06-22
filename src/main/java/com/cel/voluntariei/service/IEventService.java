package com.cel.voluntariei.service;

import com.cel.voluntariei.model.Evento;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Evento> getAllEventos();

    Optional<Evento> getEventoById(Long id);

    Evento saveEvento(Evento evento) ;

     void deleteEvento(Long id);
}

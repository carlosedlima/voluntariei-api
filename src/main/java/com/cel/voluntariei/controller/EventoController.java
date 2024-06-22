package com.cel.voluntariei.controller;

import com.cel.voluntariei.model.Evento;
import com.cel.voluntariei.service.IEventService;
import com.cel.voluntariei.service.impl.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventoController {
    @Autowired
    private IEventService eventoService;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoService.saveEvento(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        if (!eventoService.getEventoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        evento.setId(id);
        return ResponseEntity.ok(eventoService.saveEvento(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        if (!eventoService.getEventoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
package com.cel.voluntariei.controller;

import com.cel.voluntariei.dto.EventoDTO;
import com.cel.voluntariei.model.Evento;
import com.cel.voluntariei.model.Ong;
import com.cel.voluntariei.service.IEventService;
import com.cel.voluntariei.service.IOngService;
import com.cel.voluntariei.service.impl.EventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventoController {

    private static final Logger logger = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    private IEventService eventService;

    @Autowired
    private IOngService ongService;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getAllEvents() {
        try {
            List<EventoDTO> events = eventService.getAllEventos().stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            logger.error("Error fetching all events", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventById(@PathVariable Long id) {
        try {
            Optional<Evento> event = eventService.getEventoById(id);
            return event.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error fetching event by id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EventoDTO> createEvent(@RequestBody EventoDTO eventoDto) {
        try {
            Evento event = convertToEntity(eventoDto);
            Evento createdEvent = eventService.saveEvento(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdEvent));
        } catch (Exception e) {
            logger.error("Error creating event", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvent(@PathVariable Long id, @RequestBody EventoDTO eventoDto) {
        try {
            eventoDto.setId(id);
            Evento event = convertToEntity(eventoDto);
            Evento updatedEvent = eventService.saveEvento(event);
            return ResponseEntity.ok(convertToDto(updatedEvent));
        } catch (Exception e) {
            logger.error("Error updating event", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting event", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private EventoDTO convertToDto(Evento event) {
        return new EventoDTO(
                event.getId(),
                event.getNome(),
                event.getDescricao(),
                event.getData(),
                event.getOng().getId()
        );
    }

    private Evento convertToEntity(EventoDTO eventDto) {
        Evento event = new Evento();
        event.setId(eventDto.getId());
        event.setNome(eventDto.getNome());
        event.setDescricao(eventDto.getDescricao());
        event.setData(eventDto.getData());

        Optional<Ong> ong = ongService.getOngById(eventDto.getOngId());
        ong.ifPresent(event::setOng);

        return event;
    }
}
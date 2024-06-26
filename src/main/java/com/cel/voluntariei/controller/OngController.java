package com.cel.voluntariei.controller;

import com.cel.voluntariei.dto.OngDTO;
import com.cel.voluntariei.model.LoginRequest;
import com.cel.voluntariei.model.Ong;
import com.cel.voluntariei.service.IOngService;
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
@RequestMapping("/ongs")
public class OngController {

    private static final Logger logger = LoggerFactory.getLogger(OngController.class);

    @Autowired
    private IOngService ongService;

    @GetMapping
    public ResponseEntity<List<OngDTO>> getAllOngs() {
        try {
            List<OngDTO> ongs = ongService.getAllOngs().stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(ongs);
        } catch (Exception e) {
            logger.error("Error fetching all ONGs", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OngDTO> getOngById(@PathVariable Long id) {
        try {
            Optional<Ong> ong = ongService.getOngById(id);
            return ong.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error fetching ONG by id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<OngDTO> createOng(@RequestBody OngDTO ongDto) {
        try {
            Ong ong = convertToEntity(ongDto);
            Ong createdOng = ongService.saveOng(ong);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdOng));
        } catch (Exception e) {
            logger.error("Error creating ONG", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OngDTO> updateOng(@PathVariable Long id, @RequestBody OngDTO ongDto) {
        try {
            ongDto.setId(id);
            Ong ong = convertToEntity(ongDto);
            Ong updatedOng = ongService.saveOng(ong);
            return ResponseEntity.ok(convertToDto(updatedOng));
        } catch (Exception e) {
            logger.error("Error updating ONG", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id) {
        try {
            ongService.deleteOng(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting ONG", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<OngDTO> login(@RequestBody LoginRequest loginRequest) {
        try {
            logger.debug("Login attempt for email: {}", loginRequest.getEmail());
            Optional<Ong> ong = ongService.findByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha());
            if (ong.isPresent()) {
                logger.debug("Login successful for email: {}", loginRequest.getEmail());
                return ResponseEntity.ok(convertToDto(ong.get()));
            } else {
                logger.debug("Login failed for email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            logger.error("Error during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private OngDTO convertToDto(Ong ong) {
        OngDTO ongDto = new OngDTO();
        ongDto.setId(ong.getId());
        ongDto.setNome(ong.getNome());
        ongDto.setEmail(ong.getEmail());
        return ongDto;
    }

    private Ong convertToEntity(OngDTO ongDto) {
        Ong ong = new Ong();
        ong.setId(ongDto.getId());
        ong.setNome(ongDto.getNome());
        ong.setEmail(ongDto.getEmail());
        ong.setSenha(ongDto.getSenha());
        return ong;
    }
}
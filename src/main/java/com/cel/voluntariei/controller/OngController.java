package com.cel.voluntariei.controller;

import com.cel.voluntariei.dto.OngDTO;
import com.cel.voluntariei.model.LoginRequest;
import com.cel.voluntariei.model.Ong;
import com.cel.voluntariei.service.IOngService;
import com.cel.voluntariei.service.impl.OngService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ongs")
public class OngController {

    private static final Logger logger = LoggerFactory.getLogger(OngController.class);

    @Autowired
    private IOngService ongService;

    @GetMapping
    public ResponseEntity<List<Ong>> getAllOngs() {
        try {
            List<Ong> ongs = ongService.getAllOngs();
            return ResponseEntity.ok(ongs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ong> getOngById(@PathVariable Long id) {
        try {
            Optional<Ong> ong = ongService.getOngById(id);
            return ong.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Ong> createOng(@RequestBody Ong ong) {
        try {
            Ong createdOng = ongService.saveOng(ong);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOng);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ong> updateOng(@PathVariable Long id, @RequestBody Ong ong) {
        try {
            ong.setId(id);
            Ong updatedOng = ongService.saveOng(ong);
            return ResponseEntity.ok(updatedOng);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id) {
        try {
            ongService.deleteOng(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
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
                OngDTO ongDTO = new OngDTO();
                ongDTO.setId(ong.get().getId());
                ongDTO.setNome(ong.get().getNome());
                ongDTO.setEmail(ong.get().getEmail());
                return ResponseEntity.ok(ongDTO);
            } else {
                logger.debug("Login failed for email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            logger.error("Error during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

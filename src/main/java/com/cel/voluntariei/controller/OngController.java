package com.cel.voluntariei.controller;

import com.cel.voluntariei.model.Ong;
import com.cel.voluntariei.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ongs")
public class OngController {
    @Autowired
    private OngService ongService;

    @GetMapping
    public List<Ong> getAllOngs() {
        return ongService.getAllOngs();
    }

    @GetMapping("/{id}")
    public Optional<Ong> getOngById(@PathVariable Long id) {
        return ongService.getOngById(id);
    }

    @PostMapping
    public Ong createOng(@RequestBody Ong ong) {
        return ongService.saveOng(ong);
    }

    @PutMapping("/{id}")
    public Ong updateOng(@PathVariable Long id, @RequestBody Ong ong) {
        ong.setId(id);
        return ongService.saveOng(ong);
    }

    @DeleteMapping("/{id}")
    public void deleteOng(@PathVariable Long id) {
        ongService.deleteOng(id);
    }
}
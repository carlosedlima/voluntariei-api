package com.cel.voluntariei.controller;

import com.cel.voluntariei.model.Help;
import com.cel.voluntariei.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    private HelpService helpService;

    @GetMapping
    public List<Help> getAllHelps() {
        return helpService.getAllHelps();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Help> getHelpById(@PathVariable Long id) {
        Optional<Help> help = helpService.getHelpById(id);
        return help.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Help createHelp(@RequestBody Help help) {
        return helpService.saveHelp(help);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Help> updateHelp(@PathVariable Long id, @RequestBody Help help) {
        if (!helpService.getHelpById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        help.setId(id);
        return ResponseEntity.ok(helpService.saveHelp(help));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHelp(@PathVariable Long id) {
        if (!helpService.getHelpById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        helpService.deleteHelp(id);
        return ResponseEntity.noContent().build();
    }
}
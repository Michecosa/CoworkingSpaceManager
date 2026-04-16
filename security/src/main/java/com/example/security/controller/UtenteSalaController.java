package com.example.security.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.UtenteSala;
import com.example.security.service.UtenteSalaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class UtenteSalaController {

    private final UtenteSalaService utenteSalaService;

    @GetMapping
    public List<UtenteSala> getAll() {
        return utenteSalaService.getAll();
    }

    @GetMapping("/{id}")
    public UtenteSala getById(@PathVariable Long id) {
        return utenteSalaService.getById(id);
    }

    @PostMapping
    public UtenteSala prenota(
            @RequestParam Long utenteId,
            @RequestParam Long salaId,
            @RequestParam LocalDateTime inizio,
            @RequestParam LocalDateTime fine,
            @RequestParam Integer numeroPersoneTeam) {
        return utenteSalaService.prenota(utenteId, salaId, inizio, fine, numeroPersoneTeam);
    }

    @PutMapping("/{id}")
    public UtenteSala aggiorna(
            @PathVariable Long id,
            @RequestParam LocalDateTime inizio,
            @RequestParam LocalDateTime fine,
            @RequestParam Integer numeroPersoneTeam) {
        return utenteSalaService.aggiorna(id, inizio, fine, numeroPersoneTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        utenteSalaService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}

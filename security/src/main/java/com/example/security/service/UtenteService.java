package com.example.security.service;

import com.example.security.model.Utente;
import com.example.security.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public List<Utente> getAll() {
        return utenteRepository.findAll();
    }

    public Utente getById(Long id) {
        return utenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utente non trovato: " + id));
    }

    public Utente getByUsername(String username) {
        return utenteRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Utente non trovato: " + username));
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }
}
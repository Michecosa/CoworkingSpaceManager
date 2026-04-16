package com.example.security.service;

import com.example.security.model.Sala;
import com.example.security.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    public List<Sala> getAll() {
        return salaRepository.findAll();
    }

    public List<Sala> getDisponibili() {
        return salaRepository.findByDisponibile(true);
    }

    public Sala getById(Long id) {
        return salaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sala non trovata: " + id));
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public void delete(Long id) {
        salaRepository.deleteById(id);
    }
}
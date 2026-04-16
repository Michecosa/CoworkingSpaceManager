package com.example.security.service;

import com.example.security.model.UtenteSala;
import com.example.security.model.Utente;
import com.example.security.model.Sala;
import com.example.security.repository.UtenteSalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteSalaService {

    private final UtenteSalaRepository utenteSalaRepository;

    public List<UtenteSala> getByUtente(Utente utente) {
        return utenteSalaRepository.findByUtente(utente);
    }

    public List<UtenteSala> getBySala(Sala sala) {
        return utenteSalaRepository.findBySala(sala);
    }

    public UtenteSala save(UtenteSala utenteSala) {
        return utenteSalaRepository.save(utenteSala);
    }

    public void delete(Long id) {
        utenteSalaRepository.deleteById(id);
    }
}
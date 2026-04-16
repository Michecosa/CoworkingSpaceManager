package com.example.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.security.model.Sala;

@Service
public class SalaService {
    private List<Sala> sale = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Sala> getAll() {
        return sale;
    }

    public Sala getById(Long id) {
        return sale.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sala non trovata"));
    }

    public Sala crea(Sala nuovaSala) {
        nuovaSala.setId(idCounter++);
        sale.add(nuovaSala);
        return nuovaSala;
    }

    public Sala aggiorna(Long id, Sala aggiornata) {
        Sala sala = getById(id);

        sala.setCapienza(aggiornata.getCapienza());
        sala.setDisponibile(aggiornata.isDisponibile());

        return sala;
    }

    public void elimina(Long id) {
        Sala sala = getById(id);
        sale.remove(sala);
    }
}
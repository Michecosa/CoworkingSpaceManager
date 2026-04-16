package com.example.security.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.security.model.Sala;
import com.example.security.model.Utente;
import com.example.security.model.UtenteSala;
import com.example.security.repository.UtenteSalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteSalaService {

    private final UtenteSalaRepository utenteSalaRepository;
    private final UtenteService utenteService;
    private final SalaService salaService;

    public List<UtenteSala> getAll() {
        return utenteSalaRepository.findAll();
    }

    public UtenteSala getById(Long id) {
        return utenteSalaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata: " + id));
    }

    public UtenteSala prenota(Long utenteId, Long salaId, LocalDateTime inizio, LocalDateTime fine, Integer numeroPersoneTeam) {
        Utente utente = utenteService.getById(utenteId);
        Sala sala = salaService.getById(salaId);

        if (!sala.isDisponibile()) {
            throw new RuntimeException("La sala non è disponibile");
        }
        if (numeroPersoneTeam > sala.getCapienza()) {
            throw new RuntimeException("Il numero di persone supera la capienza della sala");
        }

        UtenteSala prenotazione = new UtenteSala();
        prenotazione.setUtente(utente);
        prenotazione.setSala(sala);
        prenotazione.setInizio(inizio);
        prenotazione.setFine(fine);
        prenotazione.setNumeroPersoneTeam(numeroPersoneTeam);

        return utenteSalaRepository.save(prenotazione);
    }

    public UtenteSala aggiorna(Long id, LocalDateTime inizio, LocalDateTime fine, Integer numeroPersoneTeam) {
        UtenteSala prenotazione = getById(id);

        if (numeroPersoneTeam > prenotazione.getSala().getCapienza()) {
            throw new RuntimeException("Il numero di persone supera la capienza della sala");
        }

        prenotazione.setInizio(inizio);
        prenotazione.setFine(fine);
        prenotazione.setNumeroPersoneTeam(numeroPersoneTeam);

        return utenteSalaRepository.save(prenotazione);
    }

    public void elimina(Long id) {
        utenteSalaRepository.deleteById(id);
    }
}

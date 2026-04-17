package com.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.Sala;
import com.example.security.repository.SalaRepository;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sala> getSale() {
        return salaRepository.findAll();
    }

    @PostMapping("/{id}")
    public Sala prenotaSala(@PathVariable Long id, @RequestParam int numeroPersone) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala non trovata"));

        if (sala.isDisponibile() && sala.getCapienza() >= numeroPersone) {
            sala.setDisponibile(false);
            return salaRepository.save(sala);
        }
        throw new RuntimeException("Sala non disponibile");
    }

    @PostMapping("/crea")
    public Sala creaSala(@RequestBody Sala sala) {
        sala.setDisponibile(true);
        return salaRepository.save(sala);
    }

}

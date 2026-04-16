package com.example.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.Sala;

@RestController
@RequestMapping("/sala")
public class SalaController {

    private List<Sala> sale = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public List<Sala> getSale() {
        return sale;
    }

    @PostMapping("/{id}")
    public Sala prenotaSala(@PathVariable Long id, @RequestParam int numeroPersone) {
        for (Sala sala : sale) {
            if (sala.getId().equals(id) && sala.isDisponibile() && sala.getCapienza() >= numeroPersone) {
                sala.setDisponibile(false);
                return sala;
            }
        }
        throw new RuntimeException("Sala non disponibile o non trovata");
    }

    @PostMapping("/crea")
    public Sala creaSala(@RequestParam String nome, @RequestParam int capienza) {
        Sala sala = new Sala(idCounter++, capienza, true);
        sale.add(sala);
        return sala;
    }

}

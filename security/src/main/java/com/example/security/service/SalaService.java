package com.example.security.service;

import org.springframework.stereotype.Service;

import com.example.security.repository.SalaRepository;
@Service
public class SalaService 
{
    SalaRepository salarepo;

    public SalaService(SalaRepository salarepo) {
        this.salarepo = salarepo;
    }
    
    
}

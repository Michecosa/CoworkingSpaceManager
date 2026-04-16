package com.example.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.dto.AuthRequest;
import com.example.security.dto.AuthResponse;
import com.example.security.dto.RegisterRequest;
import com.example.security.model.Utente;
import com.example.security.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servizio per la gestione dell'autenticazione:
 * - Registrazione nuovi utenti
 * - Login e generazione token JWT
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registra un nuovo utente nel database.
     * La password viene criptata prima del salvataggio.
     */
    public String register(RegisterRequest request) {
        if (utenteRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username già esistente");
        }

        Utente utente = Utente.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .ruolo(request.getRuolo())
                .build();

        utenteRepository.save(utente);
        return "Utente registrato con successo!";
    }

    /**
     * Esegue il login e restituisce un token JWT se le credenziali sono corrette.
     */
    public AuthResponse login(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Se l'autenticazione ha successo, generiamo il token
        String token = jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) auth.getPrincipal());
        return new AuthResponse(token, null);
    }
}

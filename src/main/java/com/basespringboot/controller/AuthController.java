package com.basespringboot.controller;

import com.basespringboot.dto.AuthRequestDto;
import com.basespringboot.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request) {
        try {
            // Spring Security vérifie les identifiants
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            
            // Si les identifiants sont valides, on récupère les détails de l'utilisateur
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // On génère le token JWT signé pour les requêtes futures
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("token", token)); //On retourne le token au client (qui le stockera pour le Bearer)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Identifiants invalides"));
            //// En cas d'échec d'authentification (mauvais mot de passe ou user inconnu)
        }
    }
}
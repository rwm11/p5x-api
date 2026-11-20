package com.basespringboot.security;

import com.basespringboot.model.Character;
import com.basespringboot.repository.CharacterRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CharacterRepository characterRepository;

    public UserDetailsServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Recherche le personnage qui fait office d'utilisateur
        Character character = characterRepository.findAll().stream()
                .filter(c -> username.equals(c.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        return new User(character.getUsername(), character.getPassword(), new ArrayList<>());
    }
}
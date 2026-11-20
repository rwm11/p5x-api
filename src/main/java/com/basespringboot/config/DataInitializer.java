package com.basespringboot.config;

import com.basespringboot.model.Character;
import com.basespringboot.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(CharacterRepository characterRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            boolean adminExists = characterRepository.findAll().stream()
                    .anyMatch(c -> "admin".equals(c.getUsername()));

            if (!adminExists) {
                Character admin = new Character();
                admin.setName("Admin");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRarity(5);
                characterRepository.save(admin);
                System.out.println("--> Compte admin 'admin' / 'admin123' créé avec succès !");
            }
        };
    }
}
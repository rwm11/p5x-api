package com.basespringboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "character")
@Getter 
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String codename;
    private String initialPersona;
    private String element;
    private String username;
    private String password;
    private int rarity;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Skill> skills = new ArrayList<>();
}
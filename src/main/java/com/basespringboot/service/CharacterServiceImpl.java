package com.basespringboot.service;

import com.basespringboot.dto.CharacterDto;
import com.basespringboot.model.Character;
import com.basespringboot.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character save(CharacterDto characterDto) {
        Character character = new Character();
        character.setName(characterDto.getName());
        character.setCodename(characterDto.getCodename());
        character.setInitialPersona(characterDto.getInitialPersona());
        character.setElement(characterDto.getElement());
        character.setRarity(characterDto.getRarity());
        return characterRepository.save(character);
    }
}
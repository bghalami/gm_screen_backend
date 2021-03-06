/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Character;
import com.repository.CharacterRepository;
import com.repository.TreasureRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
        
/**
 *
 * @author bghalami
 */

@RestController
public class CharacterController {
    
     @Autowired
     private CharacterRepository characterRepository;
     
     @Autowired
     private TreasureRepository treasureRepository;
     
     @GetMapping("/api/v1/characters")
     public List<Character> getCharacters() {
         return characterRepository.findAll();
     }
     
    @GetMapping("/api/v1/characters/{characterId}")
    public Optional<Character> getCharacter(@PathVariable Long characterId) {
        return characterRepository.findById(characterId);
    }
    
    @GetMapping("/api/v1/characters/play_code/{characterCode}")
    public Character getCharacter(@PathVariable String characterCode) {
        Character character = characterRepository.getByCharacterCode(characterCode);
        if(character == null) {
            throw new ResourceNotFoundException("Character not found with code " + characterCode);
        }
        return characterRepository.getByCharacterCode(characterCode);
    }
    
    @PostMapping("/api/v1/characters")
    public Character createCharacter(@Valid @RequestBody Character character) {
         Character newChar = characterRepository.save(character);
         return characterRepository.findById(newChar.getId())
                 .map(newCharacter -> {
                     newCharacter.setCreated(0);
                     newCharacter.setCharacterCode(newChar.generateCharCode());
                     return (Character) characterRepository.save(newCharacter);
                 }).orElseThrow(() -> new ResourceNotFoundException("Something went wrong, please try again"));
    }
    
    @PutMapping("/api/v1/characters/{characterId}")
    public Character updateCharacter(@PathVariable Long characterId,
                                     @Valid @RequestBody Character characterRequest) {
        return characterRepository.findById(characterId)
                .map(character -> {
                    if(characterRequest.getName() != null) {
                      character.setName(characterRequest.getName());
                    }
                    if(characterRequest.getRole() != null) {
                      character.setRole(characterRequest.getRole());
                    }
                    character.setCreated(1);
                    return characterRepository.save(character);
                }).orElseThrow(() -> new ResourceNotFoundException("Character not found with id " + characterId));
    }
    
    @DeleteMapping("/api/v1/characters/{characterId}")
    public ResponseEntity<?> deleteCharacter(@PathVariable Long characterId) {
        return characterRepository.findById(characterId)
                .map(character -> {
                    characterRepository.delete(character);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Character not found with id " + characterId));
    }
}
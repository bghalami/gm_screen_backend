/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Treasure;
import com.repository.TreasureRepository;
import com.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 *
 * @author bghalami
 */
@RestController
public class TreasureController {
    @Autowired
    private TreasureRepository treasureRepository;
    
    @Autowired
    private CharacterRepository characterRepository;
    
    @PostMapping("/api/v1/characters/{characterId}/treasures")
    public Treasure addTreasure(@PathVariable Long characterId,
                                @Valid @RequestBody Treasure treasure
                                ) {
        if(!characterRepository.existsById(characterId)) {
            throw new ResourceNotFoundException("Character not found with id " + characterId);
        }
        return characterRepository.findById(characterId)
                .map(character -> {
                    treasure.setCharacter(character);
                    return treasureRepository.save(treasure);
                }).orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id " + characterId));
    }
    
    @PutMapping("/api/v1/characters/{characterId}/treasures/{treasureId}")
    public Treasure updateTreasure(@PathVariable Long characterId,
                                   @PathVariable Long treasureId,
                                   @Valid @RequestBody Treasure treasureRequest
                                   ) {
        if(!characterRepository.existsById(characterId)) {
            throw new ResourceNotFoundException("Character not found with id " + characterId);
        }
        return treasureRepository.findById(treasureId)
                .map(treasure -> {
                    treasure.setTitle(treasureRequest.getTitle());
                    return treasureRepository.save(treasure);
                }).orElseThrow(() -> new ResourceNotFoundException("Treasure not found with id " + treasureId));
    }
    
    @DeleteMapping("/api/v1/characters/{characterId}/treasures/{treasureId}")
    public ResponseEntity<?> deleteEncounter(@PathVariable Long characterId,
                                             @PathVariable Long treasureId
                                            ) {
        if(!characterRepository.existsById(characterId)) {
            throw new ResourceNotFoundException("Character not found with id " + characterId);
        }
        return treasureRepository.findById(treasureId)
                .map(treasure -> {
                    treasureRepository.delete(treasure);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Treasure not found with id " + treasureId));
    }
}

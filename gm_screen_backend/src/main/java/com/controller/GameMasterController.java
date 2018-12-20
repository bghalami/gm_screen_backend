/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.GameMaster;
import com.repository.GameMasterRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
/**
 *
 * @author bghalami
 */

@RestController
public class GameMasterController {
    
     @Autowired
     private GameMasterRepository gameMasterRepository;
     
     @GetMapping("/game_masters")
     public Page<GameMaster> getGameMasters(Pageable pageable) {
         return gameMasterRepository.findAll(pageable);
     }
     
     @GetMapping("/game_masters/{gameMasterId}")
    public Optional<GameMaster> getGameMaster(@PathVariable Long gameMasterId) {
        return gameMasterRepository.findById(gameMasterId);
    }
    
    @PostMapping("/game_masters")
    public GameMaster createGameMaster(@Valid @RequestBody GameMaster gameMaster) {
        return (GameMaster) gameMasterRepository.save(gameMaster);
    }
    
    @PutMapping("/game_masters/{gameMasterId}")
    public GameMaster updateGameMaster(@PathVariable Long gameMasterId,
                                       @Valid @RequestBody GameMaster gameMasterRequest) {
        return gameMasterRepository.findById(gameMasterId)
                .map(gameMaster -> {
                    gameMaster.setName(gameMasterRequest.getName());
                    return gameMasterRepository.save(gameMaster);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + gameMasterId));
    }
    
    @DeleteMapping("/game_masters/{gameMasterId}")
    public ResponseEntity<?> deleteGameMaster(@PathVariable Long gameMasterId) {
        return gameMasterRepository.findById(gameMasterId)
                .map(gameMaster -> {
                    gameMasterRepository.delete(gameMaster);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + gameMasterId));
    }
}

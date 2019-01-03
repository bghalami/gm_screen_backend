package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Monster;
import com.repository.MonsterRepository;
import com.repository.EncounterRepository;
import com.repository.CampaignRepository;
import com.repository.GameMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class MonsterController {
    @Autowired
    private MonsterRepository monsterRepository;
    
    @Autowired
    private EncounterRepository encounterRepository;
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    @Autowired
    private GameMasterRepository gameMasterRepository;
    
    @GetMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters")
    public List<Monster> getMonstersByEncounterId(@PathVariable Long gameMasterId,
                                                  @PathVariable Long campaignId,
                                                  @PathVariable Long encounterId
                                                  ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!encounterRepository.existsById(encounterId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        return monsterRepository.findByEncounterId(encounterId);
    }
    
    @GetMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters/{monsterId}")
    public Optional<Monster> getMonster(@PathVariable Long gameMasterId,
                                                      @PathVariable Long campaignId,
                                                      @PathVariable Long encounterId,
                                                      @PathVariable Long monsterId
                                                      ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!encounterRepository.existsById(encounterId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        if(!monsterRepository.existsById(monsterId)) {
            throw new ResourceNotFoundException("Monster not found with id " + monsterId);
        }
        return monsterRepository.findById(monsterId);
    }
    
    @PostMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters")
    public Monster addMonster(@PathVariable Long gameMasterId,
                              @PathVariable Long campaignId,
                              @PathVariable Long encounterId,
                              @Valid @RequestBody Monster monster
                              ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        return encounterRepository.findById(encounterId)
                .map(encounter -> {
                    monster.setEncounter(encounter);
                    return monsterRepository.save(monster);
                }).orElseThrow(() -> new ResourceNotFoundException("Encounter not found with id " + encounterId));
    }
    
    @PutMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters/{monsterId}")
    public Monster updateMonster(@PathVariable Long gameMasterId,
                                 @PathVariable Long campaignId,
                                 @PathVariable Long encounterId,
                                 @PathVariable Long monsterId,
                                 @Valid @RequestBody Monster monsterRequest
                                 ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!encounterRepository.existsById(encounterId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        return monsterRepository.findById(monsterId)
                .map(monster ->{
                    if(monsterRequest.getTitle() != null) {
                      monster.setTitle(monsterRequest.getTitle());
                    }
                    return monsterRepository.save(monster);
                }).orElseThrow(() -> new ResourceNotFoundException("Monster not found with id " + monsterId));
    }
    
    @DeleteMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters/{monsterId}")
    public ResponseEntity<?> deleteMonster(@PathVariable Long gameMasterId,
                                           @PathVariable Long campaignId,
                                           @PathVariable Long encounterId,
                                           @PathVariable Long monsterId
                                           ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!encounterRepository.existsById(encounterId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        return monsterRepository.findById(monsterId)
                .map(monster ->{
                    monsterRepository.delete(monster);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Monster not found with id " + monsterId));
    }
}

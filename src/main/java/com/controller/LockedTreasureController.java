package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.LockedTreasure;
import com.model.Monster;
import com.repository.LockedTreasureRepository;
import com.repository.MonsterRepository;
import com.repository.EncounterRepository;
import com.repository.CampaignRepository;
import com.repository.GameMasterRepository;
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
public class LockedTreasureController {
    @Autowired 
    private LockedTreasureRepository lockedTreasureRepository;
    
    @Autowired
    private MonsterRepository monsterRepository;
    
    @Autowired
    private EncounterRepository encounterRepository;
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    @Autowired
    private GameMasterRepository gameMasterRepository;
    
    @PostMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters/{monsterId}/treasures")
    public LockedTreasure addMonster(@PathVariable Long gameMasterId,
                              @PathVariable Long campaignId,
                              @PathVariable Long encounterId,
                              @PathVariable Long monsterId,
                              @Valid @RequestBody LockedTreasure lockedTreasure
                              ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        return monsterRepository.findById(monsterId)
                .map(monster -> {
                    lockedTreasure.setMonster(monster);
                    return lockedTreasureRepository.save(lockedTreasure);
                }).orElseThrow(() -> new ResourceNotFoundException("Monster not found with id " + monsterId));
    }
    
    @PutMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters/{monsterId}/treasures/{lockedTreasureId}")
    public LockedTreasure addMonster(@PathVariable Long gameMasterId,
                                     @PathVariable Long campaignId,
                                     @PathVariable Long encounterId,
                                     @PathVariable Long monsterId,
                                     @PathVariable Long lockedTreasureId,
                                     @Valid @RequestBody LockedTreasure lockedTreasureRequest
                                     ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        if(!monsterRepository.existsById(monsterId)) {
            throw new ResourceNotFoundException("Monster not found with id " + monsterId);
        }
        return lockedTreasureRepository.findById(lockedTreasureId)
                .map(lockedTreasure -> {
                    if(lockedTreasureRequest.getTitle() != null){
                      lockedTreasure.setTitle(lockedTreasureRequest.getTitle());
                    }
                    if(lockedTreasureRequest.getType() != null){
                      lockedTreasure.setType(lockedTreasureRequest.getType());
                    }
                    return lockedTreasureRepository.save(lockedTreasure);
                }).orElseThrow(() -> new ResourceNotFoundException("Locked treasure not found with id " + lockedTreasureId));
    }
    
    @DeleteMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}/monsters/{monsterId}/treasures/{lockedTreasureId}")
    public ResponseEntity<?> deleteLockedTreasure(@PathVariable Long gameMasterId,
                                                  @PathVariable Long campaignId,
                                                  @PathVariable Long encounterId,
                                                  @PathVariable Long monsterId,
                                                  @PathVariable Long lockedTreasureId
                                                 ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Encounter not found with id " + encounterId);
        }
        if(!monsterRepository.existsById(monsterId)) {
            throw new ResourceNotFoundException("Monster not found with id " + monsterId);
        }
        return lockedTreasureRepository.findById(lockedTreasureId)
                .map(lockedTreasure -> {
                    lockedTreasureRepository.delete(lockedTreasure);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Locked treasure not found with id " + lockedTreasureId));
    }
}


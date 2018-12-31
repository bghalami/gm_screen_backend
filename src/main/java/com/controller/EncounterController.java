/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Encounter;
import com.repository.EncounterRepository;
import com.repository.CampaignRepository;
import com.repository.GameMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author bghalami
 */
@RestController
public class EncounterController {
    @Autowired
    private EncounterRepository encounterRepository;
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    @Autowired
    private GameMasterRepository gameMasterRepository;
    
    @GetMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters")
    public List<Encounter> getEncountersByCampaignId(@PathVariable Long gameMasterId,
                                                     @PathVariable Long campaignId
                                                    ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        return encounterRepository.findByCampaignId(campaignId);
    }
    
    @PostMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters")
    public Encounter addEncounter(@PathVariable Long gameMasterId,
                                  @PathVariable Long campaignId,
                                  @Valid @RequestBody Encounter encounter
                                  ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        return campaignRepository.findById(campaignId)
                .map(campaign -> {
                    encounter.setCampaign(campaign);
                    return encounterRepository.save(encounter);
                }).orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id " + campaignId));
    }
    
    @GetMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}")
    public Optional<Encounter> getEncounter(@PathVariable Long gameMasterId,
                                            @PathVariable Long campaignId,
                                            @PathVariable Long encounterId
                                           ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        return encounterRepository.findById(encounterId);
    }
    
    @PutMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}")
    public Encounter updateEncounter(@PathVariable Long gameMasterId,
                                     @PathVariable Long campaignId,
                                     @PathVariable Long encounterId,
                                     @Valid @RequestBody Encounter encounterRequest
                                    ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        return encounterRepository.findById(encounterId)
                .map(encounter -> {
                    encounter.setTitle(encounterRequest.getTitle());
                    return encounterRepository.save(encounter);
                }).orElseThrow(() -> new ResourceNotFoundException("Encounter not found with id " + encounterId));
    }
    
    @DeleteMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}/encounters/{encounterId}")
    public ResponseEntity<?> deleteEncounter(@PathVariable Long gameMasterId,
                                             @PathVariable Long campaignId,
                                             @PathVariable Long encounterId
                                            ) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        if(!campaignRepository.existsById(campaignId)) {
            throw new ResourceNotFoundException("Campaign not found with id " + campaignId);
        }
        return encounterRepository.findById(encounterId)
                .map(encounter -> {
                    encounterRepository.delete(encounter);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Encounter not found with id " + encounterId));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Campaign;
import com.repository.CampaignRepository;
import com.repository.GameMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private GameMasterRepository gameMasterRepository;

    @GetMapping("/api/v1/game_masters/{gameMasterId}/campaigns")
    public List<Campaign> getCampaignsByGameMasterId(@PathVariable Long gameMasterId) {
        return campaignRepository.findByGameMasterId(gameMasterId);
    }

    @PostMapping("/api/v1/game_masters/{gameMasterId}/campaigns")
    public Campaign addCampaign(@PathVariable Long gameMasterId,
                            @Valid @RequestBody Campaign campaign) {
        return gameMasterRepository.findById(gameMasterId)
                .map(gameMaster -> {
                    campaign.setGameMaster(gameMaster);
                    return campaignRepository.save(campaign);
                }).orElseThrow(() -> new ResourceNotFoundException("GM not found with id " + gameMasterId));
    }
    
    @GetMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}")
    public Optional<Campaign> getCampaign(@PathVariable Long gameMasterId, 
                                        @PathVariable Long campaignId) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }
        
        return campaignRepository.findById(campaignId);
    }

    @PutMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}")
    public Campaign updateCampaign(@PathVariable Long gameMasterId,
                               @PathVariable Long campaignId,
                               @Valid @RequestBody Campaign campaignRequest) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }

        return campaignRepository.findById(campaignId)
                .map(campaign -> {
                    campaign.setTitle(campaignRequest.getTitle());
                    return campaignRepository.save(campaign);
                }).orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id " + campaignId));
    }

    @DeleteMapping("/api/v1/game_masters/{gameMasterId}/campaigns/{campaignId}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long gameMasterId,
                                          @PathVariable Long campaignId) {
        if(!gameMasterRepository.existsById(gameMasterId)) {
            throw new ResourceNotFoundException("GM not found with id " + gameMasterId);
        }

        return campaignRepository.findById(campaignId)
                .map(campaign -> {
                    campaignRepository.delete(campaign);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id " + campaignId));

    }
}

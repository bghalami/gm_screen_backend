/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.apache.commons.lang3.*;
import java.util.*;

/**
 *
 * @author bghalami
 */

@Entity
@Table(name = "characters")
public class Character extends AuditModel {
    @Id
    @GeneratedValue(generator = "character_generator")
    @SequenceGenerator(
            name = "character_generator",
            sequenceName = "characterr_sequence",
            initialValue = 1
    )
    protected Long id;
    
    protected String name;
    
    protected String role;
    
    protected String characterCode;
    
    protected Integer created;
    
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                },
                mappedBy = "characters")
    @JsonIgnore
    private List<Encounter> encounters = new ArrayList<>();

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }
    
    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.LAZY,
               mappedBy = "character")
    private List<Treasure> treasures = new ArrayList<>();
    

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public void setTreasures(List<Treasure> treasures) {
        this.treasures = treasures;
    }

     public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getCharacterCode() {
        return characterCode;
    }

    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
    
    public String generateCharCode() {
         return RandomStringUtils.randomAlphanumeric(6);
    }
    
}


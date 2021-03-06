/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author bghalami
 */

@Entity
@Table(name = "encounters")
public class Encounter extends AuditModel {
    @Id
    @GeneratedValue(generator = "encounter_generator")
    @SequenceGenerator(
            name = "encounter_generator",
            sequenceName = "encounter_sequence",
            initialValue = 1
    )
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Campaign campaign;
    
    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.LAZY,
               mappedBy = "encounter")
    private List<Monster> monsters = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                            CascadeType.PERSIST,
                            CascadeType.MERGE
                          })
    @JoinTable(name = "encounter_characters",
            joinColumns = { @JoinColumn(name = "encounter_id") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") })
    private List<Character> characters = new ArrayList<>();

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
    
    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}

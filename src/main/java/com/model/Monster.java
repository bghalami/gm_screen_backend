package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "monsters")
public class Monster extends AuditModel {
    @Id
    @GeneratedValue(generator = "monster_generator")
    @SequenceGenerator(
            name = "monster_generator",
            sequenceName = "monster_sequence",
            initialValue = 1
    )
   
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "encounter_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Encounter encounter;
    
    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.LAZY,
               mappedBy = "monster")
    private List<LockedTreasure> lockedTreasures = new ArrayList<>();

    public List<LockedTreasure> getLockedTreasures() {
        return lockedTreasures;
    }

    public void setLockedTreasures(List<LockedTreasure> lockedTreasures) {
        this.lockedTreasures = lockedTreasures;
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

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
}
package com.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "lockedTreasures")
public class LockedTreasure extends AuditModel {
    @Id
    @GeneratedValue(generator = "locked_treasure_generator")
    @SequenceGenerator(
            name = "locked_treasure_generator",
            sequenceName = "locked_treasure_sequence",
            initialValue = 1
    )
    private Long id;
    
    @Size(min = 3, max = 100)
    private String title;
    
    private String type;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "monster_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Monster monster;

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
    
     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
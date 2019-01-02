package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author bghalami
 */

@Entity
@Table(name = "treasures")
public class Treasure extends AuditModel {
    @Id
    @GeneratedValue(generator = "treasure_generator")
    @SequenceGenerator(
            name = "treasure_generator",
            sequenceName = "treasure_sequence",
            initialValue = 1
    )
    private Long id;
    
    @Size(min = 3, max = 100)
    private String title;
    
    private String type;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Character character;

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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
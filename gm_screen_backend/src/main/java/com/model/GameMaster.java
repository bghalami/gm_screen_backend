/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 *
 * @author bghalami
 */

@Entity
@Table(name = "gameMasters")
public class GameMaster extends AuditModel {
    @Id
    @GeneratedValue(generator = "game_master_generator")
    @SequenceGenerator(
            name = "game_master_generator",
            sequenceName = "game_master_sequence",
            initialValue = 1000
    )
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @NotBlank
    @Size(min = 3, max = 40)
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

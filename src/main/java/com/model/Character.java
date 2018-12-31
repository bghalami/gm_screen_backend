/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import javax.persistence.*;
import org.apache.commons.text.RandomStringGenerator;
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
    
    protected String characterCode;
    
    protected Integer created;
    

     public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
         String string = "Ben";
         return string;
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repository;

import com.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author bghalami
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
//    List<Character> findByEncounterId(Long encounterId);
    Character getByCharacterCode(String characterCode); 
}

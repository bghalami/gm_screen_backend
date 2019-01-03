package com.repository;

import com.model.LockedTreasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LockedTreasureRepository extends JpaRepository<LockedTreasure, Long> {
    List<LockedTreasure> findByMonsterId(Long monsterId);
}
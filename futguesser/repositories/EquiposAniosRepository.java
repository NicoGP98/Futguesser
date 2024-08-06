package com.futguesser.repositories;

import com.futguesser.entities.EquiposAnios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 34661
 */
@Repository
public interface EquiposAniosRepository extends JpaRepository<EquiposAnios, Integer> {
    
}

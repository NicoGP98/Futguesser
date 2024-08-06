package com.futguesser.repositories;

import com.futguesser.entities.Equipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 34661
 */

@Repository
public interface EquiposRepository extends JpaRepository<Equipos, Integer>{
    
}

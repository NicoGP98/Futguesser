package com.futguesser.repositories;

import com.futguesser.entities.Jugadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 34661
 */
@Repository
public interface JugadoresRepository extends JpaRepository<Jugadores, Integer> {
    
}

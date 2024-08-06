package com.futguesser.repositories;

import com.futguesser.entities.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 34661
 */

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
    Optional<Roles> findByNombreRol(String nombreRol);
}

package com.futguesser.repositories;

import com.futguesser.entities.Usuarios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 34661
 */

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
    Boolean existsByNombreUsuario(String nombreUsuario);
    
   public boolean existsByEmail(String email);

    Optional<Usuarios> findByNombreUsuario(String nombreUsuario);

}

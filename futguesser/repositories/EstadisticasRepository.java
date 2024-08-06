package com.futguesser.repositories;

import com.futguesser.entities.Estadisticas;
import com.futguesser.entities.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 34661
 */

@Repository
public interface EstadisticasRepository extends JpaRepository<Estadisticas, Long>{
    List<Estadisticas> findByUsuario(Usuarios usuario);
}

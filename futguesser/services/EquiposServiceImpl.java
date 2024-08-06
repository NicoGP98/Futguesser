package com.futguesser.services;

import com.futguesser.entities.Equipos;
import com.futguesser.exceptions.EquipoNotFoundException;
import com.futguesser.repositories.EquiposRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */

@Service
public class EquiposServiceImpl implements EquiposService{
    
    @Autowired
    private EquiposRepository equipoRepository;

    @Override
    public Equipos getEquipoById(int id) throws EquipoNotFoundException {
        
        Optional<Equipos> equipo = equipoRepository.findById(id);

        if (equipo.isPresent()) {
            return equipo.get();
        } else {
            throw new EquipoNotFoundException("No se ha encontrado el equipo con id " + id);
        }

    }
}

package com.futguesser.services;

import com.futguesser.entities.EquiposAnios;
import com.futguesser.exceptions.EquipoAnioNotFoundException;
import com.futguesser.repositories.EquiposAniosRepository;
import com.futguesser.repositories.EquiposRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */
@Service
public class EquiposAniosServiceImpl {
    
    @Autowired
    private EquiposAniosRepository equipoAnioRepository;
    
    @Autowired
    private EquiposRepository equiposRepository;

    public EquiposAnios getEquipoAnioById(int id) throws EquipoAnioNotFoundException {
        
        Optional<EquiposAnios> equipoAnio = equipoAnioRepository.findById(id);

        if (equipoAnio.isPresent()) {
            return equipoAnio.get();
        } else {
            throw new EquipoAnioNotFoundException("No se ha encontrado el año de equipo del jugador con id " + id);
        }
    }    
}

package com.futguesser.services;

import com.futguesser.entities.EquiposAnios;
import com.futguesser.exceptions.EquipoAnioNotFoundException;

/**
 *
 * @author 34661
 */
public interface EquiposAniosService {
    public EquiposAnios getEquipoAnioById(int id) throws EquipoAnioNotFoundException;
}

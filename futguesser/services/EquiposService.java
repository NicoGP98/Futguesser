package com.futguesser.services;

import com.futguesser.entities.Equipos;
import com.futguesser.exceptions.EquipoNotFoundException;

/**
 *
 * @author 34661
 */
public interface EquiposService {
    public Equipos getEquipoById(int id) throws EquipoNotFoundException;
}

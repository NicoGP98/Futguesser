package com.futguesser.services;

import com.futguesser.entities.Jugadores;
import com.futguesser.exceptions.JugadorNotFoundException;
import java.util.List;

/**
 *
 * @author 34661
 */
public interface JugadoresService {
    public Jugadores getJugadorById(int id) throws JugadorNotFoundException;
    
    public Long getAllJugadores(); 
    
    public List<String> getAllJugadoresNames();
}

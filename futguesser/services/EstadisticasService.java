package com.futguesser.services;

import com.futguesser.dto.EstadisticasDTO;
import com.futguesser.entities.Estadisticas;
import com.futguesser.entities.Usuarios;
import com.futguesser.exceptions.EstadisticaNotFoundException;
import com.futguesser.exceptions.UsuarioNotFoundException;

/**
 *
 * @author 34661
 */
public interface EstadisticasService {
    public Estadisticas getEstadisticaById(Long id) throws EstadisticaNotFoundException;
    
    public void insertarEstadistica(EstadisticasDTO estadisticasDTO, Usuarios usuario) throws UsuarioNotFoundException;
    
    public EstadisticasDTO obtenerEstadisticasPorUsuario(Usuarios usuario);
}

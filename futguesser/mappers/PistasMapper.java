package com.futguesser.mappers;

import com.futguesser.domain.Pistas;
import com.futguesser.dto.PistasDTO;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */
@Service
public class PistasMapper {
    public PistasDTO map(Pistas pistas){
        PistasDTO pistasDTO = new PistasDTO();
        
        pistasDTO.nombreJugador = pistas.getNombreJugador();
        pistasDTO.stats = pistas.getStats();
        pistasDTO.trayectoria = pistas.getTrayectoria();
        pistasDTO.ucls = pistas.getUcls();
        pistasDTO.mundiales = pistas.getMundiales();
        pistasDTO.escudo = pistas.getEscudo();
        pistasDTO.nombreEquipo = pistas.getNombreEquipo();
        pistasDTO.anio = pistas.getAnio();
        pistasDTO.nacionalidad = pistas.getNacionalidad();
        
        return pistasDTO;
    }
}

package com.futguesser.services;

import com.futguesser.dto.EstadisticasDTO;
import com.futguesser.entities.Estadisticas;
import com.futguesser.entities.Usuarios;
import com.futguesser.exceptions.EstadisticaNotFoundException;
import com.futguesser.exceptions.UsuarioNotFoundException;
import com.futguesser.repositories.EstadisticasRepository;
import com.futguesser.repositories.UsuariosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */
@Service
public class EstadisticasServiceImpl implements EstadisticasService{
    
    @Autowired
    private EstadisticasRepository estadisticasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;
    

    @Override
    public Estadisticas getEstadisticaById(Long id) throws EstadisticaNotFoundException {
        Optional<Estadisticas> estadistica = estadisticasRepository.findById(id);

        if (estadistica.isPresent()) {
            return estadistica.get();
        } else {
            throw new EstadisticaNotFoundException("No se ha encontrado la estadística.");
        }
    }

    @Override
    public void insertarEstadistica(EstadisticasDTO estadisticasDTO, Usuarios usuario) throws UsuarioNotFoundException{
        Estadisticas estadisticas = new Estadisticas();

        estadisticas.setUsuario(usuario);
        
        estadisticas.setAcertado(estadisticasDTO.isAcertado());
        estadisticas.setNumIntentos(estadisticasDTO.getNumIntentos());
        
        estadisticasRepository.save(estadisticas);
    }
    
    @Override
    public EstadisticasDTO obtenerEstadisticasPorUsuario(Usuarios usuario) {
        List<Estadisticas> estadisticas = estadisticasRepository.findByUsuario(usuario);
        int aciertos = 0;
        int fallos = 0;
        int[] aciertosPorIntento = new int[6];

        for (Estadisticas stat : estadisticas) {
            if (stat.isAcertado()) {
                aciertos++;
                int intentos = stat.getNumIntentos();
                if (intentos >= 1 && intentos <= 6) {
                    aciertosPorIntento[intentos - 1]++;
                }
            } else {
                fallos++;
            }
        }

        EstadisticasDTO estadisticasDTO = new EstadisticasDTO();
        estadisticasDTO.setAciertos(aciertos);
        estadisticasDTO.setFallos(fallos);
        estadisticasDTO.setAciertosPorIntento(aciertosPorIntento);
        return estadisticasDTO;
    }
}

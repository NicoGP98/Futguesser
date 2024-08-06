package com.futguesser.services;

import com.futguesser.domain.Pistas;
import com.futguesser.entities.EquiposAnios;
import com.futguesser.entities.Jugadores;
import com.futguesser.exceptions.EquipoAnioNotFoundException;
import com.futguesser.exceptions.JugadorNotFoundException;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */
@Service
public class PistasServiceImpl {

    @Autowired
    private JugadoresServiceImpl jugadoresServiceImpl;

    public Pistas obtenerPistas(int idJugador) throws JugadorNotFoundException, EquipoAnioNotFoundException {

        Jugadores jugador = jugadoresServiceImpl.getJugadorById(idJugador);

        //Con esto dejo escogido el equipo
        EquiposAnios equipo = escogerEquipoAleatorio(jugador.getEquiposAnios());

        if (equipo == null) {
            throw new EquipoAnioNotFoundException("El equipo no se ha podido encontrar.");
        }

        return rellenarPistas(jugador, equipo);
    }

    private EquiposAnios escogerEquipoAleatorio(List<EquiposAnios> lista) {
        return lista.stream()
                .skip(new Random().nextInt(lista.size()))
                .findFirst()
                .orElse(null);
    }

    private Pistas rellenarPistas(Jugadores jugador, EquiposAnios equipo) {

        Pistas pistas = new Pistas();

        pistas.setNombreJugador(jugador.getNombre());

        pistas.setStats(jugador.getStats());

        pistas.setTrayectoria(jugador.getTrayectoria());

        pistas.setAnio(String.valueOf(equipo.getAnio()));

        pistas.setEscudo(equipo.getEquipo().getEscudo());

        pistas.setNombreEquipo(equipo.getEquipo().getNombre());

        pistas.setUcls(String.valueOf(jugador.getUcl()));

        pistas.setMundiales(String.valueOf(jugador.getMundial()));

        pistas.setNacionalidad(jugador.getNacionalidad());

        return pistas;
    }

}

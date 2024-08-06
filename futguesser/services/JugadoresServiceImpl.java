package com.futguesser.services;

import com.futguesser.entities.Jugadores;
import com.futguesser.exceptions.JugadorNotFoundException;
import com.futguesser.repositories.JugadoresRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */
@Service
public class JugadoresServiceImpl implements JugadoresService {

    @Autowired
    private JugadoresRepository repository;

    @Override
    public Jugadores getJugadorById(int id) throws JugadorNotFoundException {
        /**
         * Optional es un objeto que sirve para englobar un objeto que puede o NO estar
         * por ejemplo en este caso puede que no esté el jugador que hemos requerido por id
         * entonces el Optional estaría vacío pero no daría error.
         * 
         * En el if, utilizamos isPresent para comprobar que el Optional
         * contiene el objeto que queremos, si no lanza la excepción.
         */
        Optional<Jugadores> jugador = repository.findById(id);

        if (jugador.isPresent()) {
            return jugador.get();
        } else {
            throw new JugadorNotFoundException("No se ha encontrado el jugador con id " + id);
        }

    }

    @Override
    public Long getAllJugadores() {
       return repository.count();
    }

    @Override
    public List<String> getAllJugadoresNames() {
        List<Jugadores> jugadores = repository.findAll();
        return jugadores.stream()
                       .map(Jugadores::getNombre) // Obtengo el nombre de cada jugador
                       .collect(Collectors.toList()); // Se convierte a una lista de strings
    }
}

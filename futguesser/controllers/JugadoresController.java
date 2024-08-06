package com.futguesser.controllers;

import com.futguesser.entities.Jugadores;
import com.futguesser.services.JugadoresServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 34661
 */
@RestController
@CrossOrigin(origins = {"http://127.0.0.7:5500/","http://localhost:4200/"})
@RequestMapping("/jugadores")
public class JugadoresController {

    @Autowired
    private JugadoresServiceImpl jugadoresServiceImpl;

    //Endpoint para obtener un jugador por su id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jugadores> getJugador(@PathVariable int id) {
        try {
            Jugadores jugador = jugadoresServiceImpl.getJugadorById(id);
            
            return new ResponseEntity<>(jugador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Jugadores(), HttpStatus.NOT_FOUND);
        }
    }
    
    //Endpoint para obtener la cantidad total de jugadores que hay en la bbdd
    @GetMapping(value = "/cantidadJugadores")
    public ResponseEntity<Long> getCantidadTotalJugadores() {
        try {
            long cantidadTotal = jugadoresServiceImpl.getAllJugadores();

            return new ResponseEntity<>(cantidadTotal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
    
    //Endpoint para obtener todos los nombres de los jugadores que hay en la bbdd.
    @GetMapping("/nombres")
    public ResponseEntity<List<String>> getAllJugadoresNames() {
        List<String> nombresJugadores = jugadoresServiceImpl.getAllJugadoresNames();
        return new ResponseEntity<>(nombresJugadores, HttpStatus.OK);
    }
    
    
}

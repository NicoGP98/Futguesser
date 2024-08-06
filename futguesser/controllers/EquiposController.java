package com.futguesser.controllers;

import com.futguesser.entities.Equipos;
import com.futguesser.services.EquiposServiceImpl;
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
@RequestMapping("/equipos")
public class EquiposController {
    
    @Autowired
    private EquiposServiceImpl equiposServiceImpl;
    
    //Endpoint para obtener un equipo por su id.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Equipos> getEquipo(@PathVariable int id) {
        try {
            Equipos equipo = equiposServiceImpl.getEquipoById(id);
            
            return new ResponseEntity<>(equipo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Equipos(), HttpStatus.NOT_FOUND);
        }
    }
}

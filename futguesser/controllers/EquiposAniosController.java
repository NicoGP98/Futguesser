package com.futguesser.controllers;

import com.futguesser.entities.EquiposAnios;
import com.futguesser.services.EquiposAniosServiceImpl;
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
@RequestMapping("/equipos-anios")
public class EquiposAniosController {
    @Autowired
    private EquiposAniosServiceImpl equipoAnioServiceImpl;
    
     //Endpoint para obtener un equipo en unos años específicos por su ID.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquiposAnios> getEquipoAnio(@PathVariable int id) {
        try {
            EquiposAnios equipoAnio = equipoAnioServiceImpl.getEquipoAnioById(id);
            
            return new ResponseEntity<>(equipoAnio, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EquiposAnios(), HttpStatus.NOT_FOUND);
        }
    }
}

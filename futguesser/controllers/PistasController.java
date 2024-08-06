package com.futguesser.controllers;

import com.futguesser.domain.Pistas;
import com.futguesser.dto.PistasDTO;
import com.futguesser.exceptions.EquipoAnioNotFoundException;
import com.futguesser.exceptions.JugadorNotFoundException;
import com.futguesser.mappers.PistasMapper;
import com.futguesser.services.PistasServiceImpl;
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
@RequestMapping("/pistas")
public class PistasController {

    @Autowired
    private PistasServiceImpl pistasService;
    
    @Autowired
    private PistasMapper pistasMapper;

    //Endpoint para obtener las pistas de un jugador por su id
    @GetMapping(value = "/{idJugador}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PistasDTO> getPistas(@PathVariable int idJugador) {
        try {
            //Con pistasService traemos las pistas
            Pistas pistas = pistasService.obtenerPistas(idJugador);

            //Convertir pistas en PistasDTO y return
            PistasDTO pistasDTO = pistasMapper.map(pistas);
            
            return new ResponseEntity<>(pistasDTO, HttpStatus.OK);

        } catch (JugadorNotFoundException | EquipoAnioNotFoundException ex) {
            return new ResponseEntity<>(new PistasDTO(), HttpStatus.NOT_FOUND);
        }
    }
}

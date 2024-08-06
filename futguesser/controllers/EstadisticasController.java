package com.futguesser.controllers;

import com.futguesser.dto.EstadisticasDTO;
import com.futguesser.entities.Usuarios;
import com.futguesser.exceptions.UsuarioNotFoundException;
import com.futguesser.repositories.UsuariosRepository;
import com.futguesser.services.EstadisticasServiceImpl;
import com.futguesser.services.UserDetailsServiceImpl;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 34661
 */

@RestController
@CrossOrigin(origins = {"http://127.0.0.7:5500/","http://localhost:4200/"})
@RequestMapping("/estadisticas")
public class EstadisticasController {
    @Autowired
    private EstadisticasServiceImpl estadisticasServiceImpl;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    private UsuariosRepository usuariosRepository;
    
    //Endpoint para crear una estadística
    @PostMapping("/insertaStat")
    public ResponseEntity<?> crearEstadistica(Principal principal,@RequestBody EstadisticasDTO estadisticasDTO) {
        try {
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
            Usuarios usuario = usuariosRepository.findByNombreUsuario(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            estadisticasServiceImpl.insertarEstadistica(estadisticasDTO, usuario);
            return ResponseEntity.ok("{\"message\": \"Estadística creada correctamente\"}");
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Usuario no encontrado\"}");
        }
    }
    
    //Endpoint para obtener las estadísticas de un usuario concreto
    @GetMapping("/statsUsuario")
    public ResponseEntity<?> obtenerEstadisticasUsuario(Principal principal) {
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
        Usuarios usuario = usuariosRepository.findByNombreUsuario(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        EstadisticasDTO estadisticasDTO = estadisticasServiceImpl.obtenerEstadisticasPorUsuario(usuario);
        return ResponseEntity.ok(estadisticasDTO);
    }
}

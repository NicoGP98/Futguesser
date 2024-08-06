package com.futguesser.controllers;

import com.futguesser.entities.Roles;
import com.futguesser.config.JwtTokenProvider;
import com.futguesser.dto.AuthResponseDTO;
import com.futguesser.dto.LoginDTO;
import com.futguesser.dto.RegistroDTO;
import com.futguesser.entities.Usuarios;
import com.futguesser.repositories.RolesRepository;
import com.futguesser.repositories.UsuariosRepository;
import com.futguesser.services.UserDetailsServiceImpl;
import java.security.Principal;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/api/auth/")
@CrossOrigin(origins = {"http://127.0.0.7:5500/","http://localhost:4200/"})
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RolesRepository rolesRepository;
    
    @Autowired
    private UsuariosRepository usuariosRepository;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    //Endpoint para registrar usuarios con rol admin
    @PostMapping("registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        if(usuariosRepository.existsByEmail(registroDTO.getEmail()) || usuariosRepository.existsByNombreUsuario(registroDTO.getNombreUsuario())){
            return new ResponseEntity<>("El usuario ya existe. Cambie el email o el nombre de usuario", HttpStatus.BAD_REQUEST);
        }
        
        Usuarios usuario = new Usuarios();
        
        usuario.setNombreUsuario(registroDTO.getNombreUsuario());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        
        Roles roles = rolesRepository.findByNombreRol("ADMIN").orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));
        
        usuario.setRoles(Collections.singletonList(roles));
        
        usuariosRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado correctamente.", HttpStatus.OK);
    }
    
    //Endpoint para hacer login y obtener un token
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> loginUsuario(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getNombreUsuario(),loginDTO.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtTokenProvider.generarToken(authentication);
        
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    //Endpoint para obtener el usuario que hay logueado
    @GetMapping("usuarioActual")
    public Usuarios obtenerUsuarioActual(Principal principal) {
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
        Usuarios usuario = usuariosRepository.findByNombreUsuario(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario;
    }
      
}


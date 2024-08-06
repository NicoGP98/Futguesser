package com.futguesser.services;

import com.futguesser.entities.Roles;
import com.futguesser.entities.Usuarios;
import com.futguesser.repositories.UsuariosRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsuariosRepository usuarioRepository;
    
    public Collection<GrantedAuthority> mapToAuthorities(List<Roles> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombreRol())).collect(Collectors.toList());
    }
    
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuarios usuarios = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuarios.getNombreUsuario(), usuarios.getPassword(), mapToAuthorities(usuarios.getRoles()));
    }
    
}

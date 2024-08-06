package com.futguesser.services;

import com.futguesser.entities.Usuarios;
import com.futguesser.exceptions.UsuarioNotFoundException;
import com.futguesser.repositories.UsuariosRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 34661
 */
@Service
public class UsuariosServiceImpl implements UsuariosService{
    
    @Autowired
    private UsuariosRepository usuarioRepository;

    @Override
    public Usuarios getUsuarioById(Long id) throws UsuarioNotFoundException {
        Optional<Usuarios> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new UsuarioNotFoundException("No se ha encontrado el usuario");
        }
    }

    @Override
    public Optional<Usuarios> getUsuarioByUsername(String nombreUsuario) throws UsuarioNotFoundException {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
}

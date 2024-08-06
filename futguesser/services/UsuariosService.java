package com.futguesser.services;

import com.futguesser.entities.Usuarios;
import com.futguesser.exceptions.UsuarioNotFoundException;
import java.util.Optional;

/**
 *
 * @author 34661
 */
public interface UsuariosService {
    public Usuarios getUsuarioById(Long id) throws UsuarioNotFoundException;

    public Optional<Usuarios> getUsuarioByUsername(String nombreUsuario) throws UsuarioNotFoundException;
}

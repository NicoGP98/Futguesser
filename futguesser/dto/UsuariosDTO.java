package com.futguesser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author 34661
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuariosDTO {
    
    public Long idUsuario;
    
    public String nombreUsuario;
    
    public String email;
    
    public String password;
}

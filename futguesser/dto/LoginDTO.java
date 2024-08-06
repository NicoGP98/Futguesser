package com.futguesser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author 34661
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    
    private String nombreUsuario;
    
    private String password;
}

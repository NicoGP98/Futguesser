package com.futguesser.entities;

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
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
        private String nombreUsuario;
        
        private String password;
}

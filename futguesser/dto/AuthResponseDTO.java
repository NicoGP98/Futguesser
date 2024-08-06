package com.futguesser.dto;

import lombok.Data;

/**
 *
 * @author 34661
 */
/*En esta clase se devolverá la información con el token y el tipo de este*/
@Data
public class AuthResponseDTO {
    
    private String token;
    
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String token) {
        this.token = token;
    } 
}

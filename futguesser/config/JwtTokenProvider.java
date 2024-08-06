package com.futguesser.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author 34661
 */

/**
 * Proveedor de tokens JWT que se encarga de generar, validar y extraer información de los tokens JWT.
 */
@Component
public class JwtTokenProvider {
    
    /**
     * Método para generar un token JWT a partir de la autenticación del usuario.
     * @param authentication La autenticación del usuario.
     * @return El token JWT generado.
     */
    public String generarToken(Authentication authentication){
        String nombreUsuario = authentication.getName();
        
        Date tiempoActual = new Date();
        
        Date expiracionToken = new Date(tiempoActual.getTime() + ConstantesSeguridad.JWT_EXPIRATION_TOKEN);
    
        /*Generamos el token*/
        String token = Jwts.builder()
                .setSubject(nombreUsuario)
                .setIssuedAt(new Date())
                .setExpiration(expiracionToken)
                .signWith(SignatureAlgorithm.HS512, ConstantesSeguridad.JWT_FIRMA)
                .compact();
        
        return token;
    }
    
    /**
     * Método para obtener el nombre de usuario a partir de un token JWT.
     * @param token El token JWT.
     * @return El nombre de usuario extraído del token.
     */
    public String obtenerNombreUsuarioDeJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(ConstantesSeguridad.JWT_FIRMA)
                .parseClaimsJws(token)
                .getBody();
        
        return claims.getSubject();
    }
    
    /**
     * Método para validar un token JWT.
     * @param token El token JWT a validar.
     * @return true si el token es válido, false en caso contrario.
     */
    public Boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_FIRMA).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            // Si el token ha expirado o es incorrecto, lanzar una excepción de autenticación.
            throw new AuthenticationCredentialsNotFoundException("Jwt ha expirado o es incorrecto");
        }
    }
}

package com.futguesser.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author 34661
 */

/**
     * Método para manejar la excepción de autenticación.
     * param request: La solicitud HTTP que desencadenó la excepción.
     * param response: La respuesta HTTP que se enviará al cliente.
     * param authException: La excepción de autenticación que se produjo.
     * throws IOException: Si ocurre un error de entrada/salida al enviar la respuesta.
     * throws ServletException: Si ocurre un error al procesar la solicitud.
     */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Envía una respuesta de error HTTP 401 (Unauthorized) con el mensaje de la excepción de autenticación al cliente.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }  
}

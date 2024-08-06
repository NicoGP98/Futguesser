package com.futguesser.config;

import com.futguesser.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author 34661
 */

/**
 * Filtro para autenticación JWT que intercepta cada solicitud HTTP una sola vez.
 * Este filtro valida la información del token JWT en la cabecera de autorización de la solicitud.
 * Si el token es válido, establece la autenticación del usuario en el contexto de seguridad de Spring.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    /**
     * Método para obtener el token JWT de la solicitud HTTP.
     * param: request: La solicitud HTTP.
     * return: El token JWT si está presente en la cabecera de autorización, de lo contrario, null.
     */
    private String obtenerTokenDeSolicitud(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        } else{
            return null;
        }
    }

    /**
     * Método para validar la información del token JWT y establecer la autenticación del usuario.
     * Si el token es válido, se establece la autenticación del usuario en el contexto de seguridad de Spring.
     * param request: La solicitud HTTP.
     * param response: La respuesta HTTP.
     * param filterChain: El filtro de cadena.
     * throws ServletException Si ocurre un error en el servlet.
     * throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        
        String token = obtenerTokenDeSolicitud(request);
        
        if(StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)){
            
            String nombreUsuario = jwtTokenProvider.obtenerNombreUsuarioDeJwt(token);
            
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
            
            // Obtener roles del usuario a partir de sus autorizaciones.
            List<String> rolesUsuario = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            
            // Verificar si el usuario tiene roles de "USER" o "ADMIN".
            if(rolesUsuario.contains("USER") || rolesUsuario.contains("ADMIN")){
                // Establecer la autenticación del usuario en el contexto de seguridad de Spring.
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Continuar con la cadena de filtros.
        filterChain.doFilter(request, response);
    }  
}
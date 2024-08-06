package com.futguesser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author 34661
 */

/**
 * Configuración de la seguridad de la aplicación.
 */
@Configuration
@EnableWebSecurity // Activa la seguridad web en la aplicación
public class SecurityConfig {
    
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    /**
     * Bean para obtener el AuthenticationManager, que verifica la información de los usuarios que inician sesión en la API.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    /**
     * Bean para encriptar las contraseñas utilizando BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Bean para el filtro de autenticación JWT.
     */
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    
    /**
     * Bean para configurar la cadena de filtros de seguridad en la aplicación.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilita la protección contra CSRF
                .exceptionHandling() // Permite el manejo de excepciones
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) /* Establece un punto
                de entrada personalizado de autenticación para manejar autenticaciones no autorizadas*/
                .and()
                .sessionManagement() // Permite la gestión de sesiones
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
                // Indica que no se deben crear sesiones
                .and()
                .authorizeHttpRequests() // Todas las solicitudes HTTP deben estar autorizadas
                .requestMatchers("/api/auth/**", "/jugadores/**", "/pistas/**").permitAll() 
                // Permite el acceso a ciertas URL sin autenticación
                .anyRequest().authenticated() // Todas las demás solicitudes requieren autenticación
                .and()
                .httpBasic(); // Utiliza autenticación HTTP básica
                
          http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); 
            // Agrega el filtro de autenticación JWT antes del filtro de autenticación básica de usuario y contraseña
          return http.build();
    }
}


package com.example.apibus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@CrossOrigin(origins = "*")
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> new CorsConfiguration().applyPermitDefaultValues())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // Rotas
                        .requestMatchers(HttpMethod.GET, "/rota/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rota").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rota/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rota/*/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/rota").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/rota/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/rota/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/rota/*").hasRole("ADMIN")

                        // User
                        .requestMatchers(HttpMethod.GET, "/usuario/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/*/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/").permitAll()

                        // Login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/*/*").permitAll()

                        // Favoritos
                        .requestMatchers(HttpMethod.PUT, "/adicionar-favorito/*/*").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/remover-favorito/*/*").permitAll()

                        // Onibus
                        .requestMatchers(HttpMethod.GET, "/onibus").permitAll()
                        .requestMatchers(HttpMethod.GET, "/onibus/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/onibus/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/onibus/*/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/onibus/*/*/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/onibus").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/onibus/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/rota/{rotaId}/addOnibus/{onibusId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/onibus/*").hasRole("ADMIN")

                        // Paradas
                        .requestMatchers(HttpMethod.GET, "/parada").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parada/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parada/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parada/*/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/parada").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/parada/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/parada/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/rota/{rotaId}/addParada/{paradaId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/parada/*").hasRole("ADMIN")

                        // Cidade
                        .requestMatchers(HttpMethod.GET, "/cidade/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cidade/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cidade").hasRole("ADMIN")

                        











                        .anyRequest().authenticated()
                        
                        
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

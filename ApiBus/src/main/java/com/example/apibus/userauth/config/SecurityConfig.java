package com.example.apibus.userauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilterConfig securityFilterConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.GET, "/rota/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/onibus/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/parada/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuario/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cidade/").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/rota").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/onibus").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/parada").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cidade").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/rota/{rotaId}/addParada/{paradaId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/rota/{rotaId}/addOnibus/{onibusId}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/userAuths").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilterConfig, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

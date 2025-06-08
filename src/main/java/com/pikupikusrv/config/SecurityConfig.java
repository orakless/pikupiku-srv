package com.pikupikusrv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de la sécurité pour l'application Spring Boot.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean pour configurer la chaîne de filtres de sécurité.
     *
     * @param http L'objet HttpSecurity utilisé pour configurer les règles de sécurité.
     * @return La chaîne de filtres de sécurité configurée.
     * @throws Exception Si une erreur survient lors de la configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login", "/maps/**", "/scores/**").permitAll()  // Endpoints publics
                        .anyRequest().authenticated()  // Tout le reste nécessite une authentification
                )
                .httpBasic(Customizer.withDefaults()); // Utilisation de l'authentification HTTP basique

        return http.build();
    }

    /**
     * Bean pour fournir le gestionnaire d'authentification.
     *
     * @param config La configuration d'authentification.
     * @return Le gestionnaire d'authentification.
     * @throws Exception Si une erreur survient lors de la récupération du gestionnaire.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean pour encoder les mots de passe.
     *
     * @return L'encodeur de mots de passe basé sur BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
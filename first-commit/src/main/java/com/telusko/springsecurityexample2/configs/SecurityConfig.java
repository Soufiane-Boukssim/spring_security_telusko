package com.telusko.springsecurityexample2.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //Désactiver la protection CSRF (Cross-Site Request Forgery)
                .csrf(AbstractHttpConfigurer::disable)
                //Demander authentification pour chaque requete
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())
                //Afficher le formulaire d'authentification dans browser
                //.formLogin(Customizer.withDefaults());
                //Permettre l'authentification via Basic Auth
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    @Bean
    //l'interface AuthenticationProvider a deux méthodes principales:
    //supports : Pour vérifier si le type d'authentification est supporté.
    //authenticate : Pour vérifier si les données d'authentification sont correctes et retourner un objet Authentication complet.
    public AuthenticationProvider authenticationProvider(){
        //signifie que l'authentification se fera à partir des données stockées en base de données ou dans tout autre système compatible
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        //Encode puis vérifie le mot de passe fourni si correspond au mot de passe stocké
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        //Se connecte à la classe MyUserDetailsService pour charger les informations des utilisateurs
        provider.setUserDetailsService(userDetailsService);
        //méthode retourne l'instance configurée de DaoAuthenticationProvider
        return provider;
    }



}
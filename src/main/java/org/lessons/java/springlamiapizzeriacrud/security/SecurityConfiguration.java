package org.lessons.java.springlamiapizzeriacrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //x definire un AuthenticationProvider ho bisogno di:
    //- uno UserDetailsService
    //- un PasswordEncoder


    //questo è lo UserDetailsService
    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    //questo è PasswordEncoder che deduce algoritmo di encoding da una stringa nella password stessa
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }


    /*
    /ingredients solo ADMIN
    /pizzas, /pizzas/{id} ADMIN e USER
    /pizzas/create solo ADMIN
    /pizzas/edit/** solo ADMIN
    /pizzas/delete/{id} solo ADMIN (è una post)
    /deals/** solo ADMIN
     */

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //definisco la catena di filtri
        http.authorizeHttpRequests()
                .requestMatchers("/ingredients").hasAuthority("ADMIN")
                .requestMatchers("/pizzas/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/pizzas/create").hasAuthority("ADMIN")
                .requestMatchers("/pizzas/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/deals/**").hasAuthority("ADMIN")
                //per dire che tutte le PostMapping sono solo x admin
                .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        //disabilito csrf per poter invocare api Postman
        http.csrf().disable();
        return http.build();
    }
}

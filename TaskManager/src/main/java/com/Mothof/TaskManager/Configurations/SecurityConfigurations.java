package com.Mothof.TaskManager.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurations {

    @Autowired
    private UserDetailsService userDetailsService;

    /*
    Set up the application such that client requests for the login and register paths are exempt
    from user credentials authentication.
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/", "/register", "/loginFormStyles.css",
                        "/registrationFormStyles.css", "/js/registrationFormDOM.js").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
    /*
    DaoAuthenticationProvider object connects to the database and is used to verify
    user provided login credentials against database records.
    */
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        daoAuthProvider.setUserDetailsService(userDetailsService);
        return daoAuthProvider;
    }
}

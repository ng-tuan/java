package com.udacity.superduperdrive.config;

import com.udacity.superduperdrive.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(this.authenticationService);

        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll().requestMatchers(new AntPathRequestMatcher("/signup")).permitAll().anyRequest().authenticated();
        }).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll());

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }

}

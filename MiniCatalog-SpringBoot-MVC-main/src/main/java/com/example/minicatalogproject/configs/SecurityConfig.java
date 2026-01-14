package com.example.minicatalogproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/assets/**", "/", "/api/**").permitAll()
                        .requestMatchers("/login", "/access-denied").anonymous()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/app/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(
                form ->
                        form.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/", true)
        ).logout(
                LogoutConfigurer::permitAll
        ).exceptionHandling(
                config -> config.accessDeniedPage("/access-denied")
        );

        return httpSecurity.build();
    }
}

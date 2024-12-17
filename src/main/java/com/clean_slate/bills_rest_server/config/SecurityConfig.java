package com.clean_slate.bills_rest_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/auth/register", "/auth/login").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(form ->
            form
                .loginPage("/auth/login")
                .defaultSuccessUrl("/home", true)
        )
        .logout(logout ->
            logout
                .logoutSuccessUrl("/auth/login")
        )
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

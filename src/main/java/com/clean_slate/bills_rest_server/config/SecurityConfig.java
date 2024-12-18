package com.clean_slate.bills_rest_server.config;

import com.clean_slate.bills_rest_server.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf( csrf -> csrf.disable())
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/auth/register", "/auth/login").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(form ->
            form
                .loginPage("/auth/login")
                .loginProcessingUrl("/login") // Default Spring Security POST endpoint
                .defaultSuccessUrl("/home", true)
                .failureUrl("/auth/login?error=true")
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutSuccessUrl("/auth/login")
                .permitAll()
        )
        .userDetailsService(customUserDetailsService) // Register the custom service
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

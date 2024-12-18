package com.clean_slate.bills_rest_server.service;

import com.clean_slate.bills_rest_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.clean_slate.bills_rest_server.model.User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return User.builder()
        .username(user.getUsername())
        .password(user.getPassword()) // Password should already be hashed
        .roles("USER") // Set roles or authorities
        .build();
  }
}

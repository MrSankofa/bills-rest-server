package com.clean_slate.bills_rest_server.config;

import com.clean_slate.bills_rest_server.model.User;
import com.clean_slate.bills_rest_server.repository.UserRepository;
import com.clean_slate.bills_rest_server.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private CustomUserDetailsService customUserDetailsService;

  public CustomUserDetailsServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void loadUserByUsername_UserExists_ReturnsUserDetails() {
    // Arrange
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("encodedPassword");
    when(userRepository.findByUsername("testUser")).thenReturn(user);

    // Act
    UserDetails userDetails = customUserDetailsService.loadUserByUsername("testUser");

    // Assert
    assertNotNull(userDetails);
    assertEquals("testUser", userDetails.getUsername());
    assertEquals("encodedPassword", userDetails.getPassword());
  }

  @Test
  void loadUserByUsername_UserNotFound_ThrowsException() {
    // Arrange
    when(userRepository.findByUsername("unknownUser")).thenReturn(null);

    // Act & Assert
    assertThrows(UsernameNotFoundException.class, () ->
        customUserDetailsService.loadUserByUsername("unknownUser")
    );
  }
}

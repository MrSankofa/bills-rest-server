package com.clean_slate.bills_rest_server.controller;

import com.clean_slate.bills_rest_server.model.User;
import com.clean_slate.bills_rest_server.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserRepository userRepository;

  @MockitoBean
  private PasswordEncoder passwordEncoder;

  @Test
  public void testRegisterUser_Success() throws Exception {
    when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

    mockMvc.perform(post("/auth/register")
            .param("username", "testUser")
            .param("password", "password"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login"));

    verify(userRepository).save(any(User.class));
    verify(passwordEncoder).encode("password");
  }

  @Test
  public void testRegisterUser_NullPassword() throws Exception {
    mockMvc.perform(post("/auth/register")
            .param("username", "testUser"))
        .andExpect(status().isOk())
        .andExpect(view().name("register"));

    verify(userRepository, never()).save(any(User.class));
    verify(passwordEncoder, never()).encode(anyString());
  }
}

package com.clean_slate.bills_rest_server.controller;

import com.clean_slate.bills_rest_server.model.User;
import com.clean_slate.bills_rest_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Show Registration Form
  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    System.out.println("model = " + model);
    model.addAttribute("user", new User());
    return "register"; // Returns the Thymeleaf registration form view
  }

  // Handle Registration Submission
  @PostMapping("/register")
  public String registerUser(@ModelAttribute User user) {

    if (user.getUsername() == null || user.getPassword() == null) {
      System.out.println("Password is null");
      return "register";
    }
    System.out.println("user: " + user);
    user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
    userRepository.save(user); // Save the user to the database
    return "redirect:/login"; // Redirect to login page
  }

  // Show Login Form
  @GetMapping("/login")
  public String showLoginForm() {
    return "login"; // Returns the Thymeleaf login form view
  }


}

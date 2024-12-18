package com.clean_slate.bills_rest_server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String home(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("Authentication: " + auth);
    System.out.println("Authentication name: " + auth.getName());
    System.out.println("is authenticated: " + auth.isAuthenticated());

    String username = auth.getName(); // Get the logged-in user's username

    // Add the username to the model
    model.addAttribute("username", username);
    return "home";
  }
}

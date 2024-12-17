package com.clean_slate.bills_rest_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/Home")
  public String home() {
    return "home";
  }
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login-error")
public class LoginErrorController {
    @GetMapping
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

}

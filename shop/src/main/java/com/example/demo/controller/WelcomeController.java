package com.example.demo.controller;

import com.example.demo.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {


    @GetMapping
    public String getAll(Users users){
        return "users";
    }
}

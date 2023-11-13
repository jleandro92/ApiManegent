package com.example.apibus.userauth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/hello")
    public String home(){
        return "Bem-vindo!";
    }

    @GetMapping("/log")
    public String login(){
        return "Olá! Faça login.";
    }

}

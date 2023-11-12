package com.ms.notific.controllers;

import com.ms.notific.models.EmailUserModel;
import com.ms.notific.repositories.EmailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmailUserController {

    private final EmailUserRepository emailUserRepository;


    @Autowired
    public EmailUserController(EmailUserRepository emailUserRepository, RestTemplate restTemplate) {
        this.emailUserRepository = emailUserRepository;
    }

    @GetMapping("/list")
    public List<EmailUserModel> listUsuarios() {
        List<EmailUserModel> listUsuarios = emailUserRepository.findAll();
        return listUsuarios;
    }

    @PostMapping("/list")  // Adicione este método para aceitar requisições POST
    public ResponseEntity<String> salvarUsuario(@RequestBody EmailUserModel usuario) {
        emailUserRepository.save(usuario);
        return ResponseEntity.ok("Usuário salvo com sucesso!");
    }
}


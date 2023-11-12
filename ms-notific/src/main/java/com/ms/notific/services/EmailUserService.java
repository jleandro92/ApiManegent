package com.ms.notific.services;

import com.example.apibus.entidade.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailUserService {

    @Autowired
    private RestTemplate restTemplate;

    public void enviarDados(Usuario dado){
        String url = "http://localhost:8081/usuario";
        ResponseEntity<String> resposta = restTemplate.postForEntity(url, dado, String.class);
        System.out.println(resposta.getBody());
    }
}

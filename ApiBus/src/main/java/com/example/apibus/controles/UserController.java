package com.example.apibus.controles;

import java.util.List;
import java.util.Optional;

import com.example.apibus.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.apibus.entidade.Rota;
import com.example.apibus.entidade.Usuario;
import com.example.apibus.repositorys.RotaRepository;
import com.example.apibus.repositorys.UsuarioRepository;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestTemplate restTemplate;

    public UserController(UsuarioService usuarioService, RestTemplate restTemplate){
        this.usuarioService = usuarioService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/usuario/")
    public List<Usuario> listUsuarios(){

        List<Usuario> listUsuarios = usuarioRepository.findAll();

        return listUsuarios;
    }

    @GetMapping("/usuario/{usuarioId}/rota")
    public List<Usuario> listUser(){

        List<Usuario> listUser = usuarioRepository.findAll();

        return listUser;
    }

    @PutMapping("/adicionarfavorito/{usuarioId}/{rotaId}")
    public ResponseEntity<Object> addFavorito (@PathVariable("usuarioId") Long usuarioId, @PathVariable("rotaId") Long rotaId){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<Rota> rotaOptional = rotaRepository.findById(rotaId);

        if(usuarioOptional.isPresent() && rotaOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            Rota rota = rotaOptional.get();

            usuario.getRotas().add(rota);
            rota.getUsuarios().add(usuario);

            rotaRepository.save(rota);
            usuarioRepository.save(usuario);
            
            return ResponseEntity.status(HttpStatus.OK).body("Rota favoritada com sucesso!");
            
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel adcionar!");
    
     }

     @PutMapping("/removerfavorito/{usuarioId}/{rotaId}")
    public ResponseEntity<Object> removeFavorito (@PathVariable("usuarioId") Long usuarioId, @PathVariable("rotaId") Long rotaId){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<Rota> rotaOptional = rotaRepository.findById(rotaId);

        if(usuarioOptional.isPresent() && rotaOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            Rota rota = rotaOptional.get();

            usuario.getRotas().remove(rota);
            rota.getUsuarios().remove(usuario);

            rotaRepository.save(rota);
            usuarioRepository.save(usuario);


            return ResponseEntity.status(HttpStatus.OK).body("Rota Desvinculada com sucesso!");
            
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel remover, tente de novo!");
    
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> salvarDado(@RequestBody Usuario dado) {

        //usuarioService.salvarDados(dado);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Usuario> requestEntity = new HttpEntity<>(dado, headers);

        String outraApiUrl = "http://localhost:8082/list";

        ResponseEntity<String> respostaDaOutraApi = restTemplate.postForEntity(outraApiUrl, requestEntity, String.class);

        if (respostaDaOutraApi.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Dado enviado para outra API com sucesso!");
        } else {
            return ResponseEntity.status(respostaDaOutraApi.getStatusCode()).body("Erro ao enviar dados para a outra API.");
        }
    }
}

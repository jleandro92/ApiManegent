package com.example.apibus.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apibus.entidade.Rota;
import com.example.apibus.entidade.Usuario;
import com.example.apibus.repositorys.RotaRepository;
import com.example.apibus.repositorys.UsuarioRepository;

@RestController
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

}

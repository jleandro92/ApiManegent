package com.example.apibus.controles;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.apibus.dtos.GoogleLogin;
import com.example.apibus.dtos.TokenDto;
import com.example.apibus.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.apibus.entidades.Rota;
import com.example.apibus.entidades.Usuario;
import com.example.apibus.repositorys.RotaRepository;
import com.example.apibus.repositorys.UsuarioRepository;
import com.ms.notific.services.NotificacaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {
    
    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    NotificacaoService notificacaoService;

    @GetMapping("/usuario/")
    public List<Usuario> listUsuarios(){

        List<Usuario> listUsuarios = usuarioRepository.findAll();

        return listUsuarios;
    }
    @PostMapping("/login")
    public ResponseEntity<Object >loginGoogle(@RequestBody @Valid GoogleLogin googleLogin){

        try{

            UserDetails user = usuarioRepository.findByEmail(googleLogin.getEmail());

            if(user == null){
                Usuario newUser = new Usuario();
                newUser.setEmail(googleLogin.getEmail());
                newUser.setNomeUser(googleLogin.getNomeUser());
                newUser.setIdGoogle(googleLogin.getIdGoogle());
                
                Usuario savedUser = usuarioRepository.save(newUser);

                Collection<? extends GrantedAuthority> authorities = null;
                if(savedUser.getNivel() == 100){
                    authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }else{
                    authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                }

                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = tokenService.generateToken(savedUser);

                // Adicione informações extras ao token
                Map<String, Object> tokenData = new HashMap<>();
                tokenData.put("email", savedUser.getEmail());
                tokenData.put("name", savedUser.getNomeUser());
                tokenData.put("sub", savedUser.getIdGoogle());
                tokenData.put("id", savedUser.getId());

                return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenService.generateToken(user.getUsername());

            return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro ao realizar login.");
        }
    }

    @PutMapping("/adicionar-favorito/{usuarioId}/{rotaId}")
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
            
            notificacaoService.notificaAlteracaoRota(usuario, rota,"Rota favoritada com sucesso!");
            return ResponseEntity.status(HttpStatus.OK).body("Rota favoritada com sucesso!");
            
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel adcionar!");
    
     }

     @PutMapping("/remover-favorito/{usuarioId}/{rotaId}")
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

     @GetMapping("/lista-favorito/{usuarioId}/rotas")
     public List<Rota> listRotas(@PathVariable("usuarioId") Long usuarioId) {
         Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
     
         if (usuarioOptional.isPresent()) {
             Usuario usuario = usuarioOptional.get();
             List<Rota> rotasDoUsuario = usuario.getRotas();
             return rotasDoUsuario;
         } else {
             return Collections.emptyList();
         }
     }

}

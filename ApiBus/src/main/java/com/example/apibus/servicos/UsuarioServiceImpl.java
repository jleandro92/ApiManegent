package com.example.apibus.servicos;

import com.example.apibus.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apibus.entidade.Usuario;



@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public Usuario adicionarfavorito(Long usuarioId, Long rotaId) {
   
        throw new UnsupportedOperationException("Unimplemented method 'adicionarfavorito'");
    }

    @Override
    public Usuario removerfavorito(Long usuarioId, Long rotaId) {
       
        throw new UnsupportedOperationException("Unimplemented method 'removerfavorito'");
    }

    @Autowired
    private UsuarioRepository usuarioRepository;  // Suponha que você tenha um repositório chamado UsuarioRepository

    @Override
    public void salvarDados(Usuario usuario) {

        usuarioRepository.save(usuario);
    }







}

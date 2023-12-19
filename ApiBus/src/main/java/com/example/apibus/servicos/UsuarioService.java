package com.example.apibus.servicos;

import com.example.apibus.entidades.Usuario;

public interface UsuarioService {

    Usuario adicionarfavorito(Long usuarioId, Long rotaId);

    Usuario removerfavorito(Long usuarioId, Long rotaId);
}

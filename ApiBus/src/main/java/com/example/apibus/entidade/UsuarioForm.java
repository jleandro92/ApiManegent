package com.example.apibus.entidade;

import lombok.Data;

@Data
public class UsuarioForm {

    private String email;
    private String nomeUser;
    private String idGoogle;
    private Integer nivel;
    private Long rota_id;
    
}

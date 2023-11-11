package com.example.apibus.entidade;


import lombok.Data;

@Data
public class ParadaForm {

    private String paradaNome;
    private Integer posicao;
    private Long rota_id;
    private Long cidade_Id;
}

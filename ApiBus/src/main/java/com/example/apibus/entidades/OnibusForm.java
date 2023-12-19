package com.example.apibus.entidades;

import java.math.BigInteger;

import lombok.Data;

@Data

public class OnibusForm {
    private String numOnibus;
    private String horaSaida;
    private String horaChegada;
    private Float  valorLinha;
    private String acessibilidade;
    private BigInteger week;
    private Long rota_id;



}

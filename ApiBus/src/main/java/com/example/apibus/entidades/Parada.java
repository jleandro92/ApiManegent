package com.example.apibus.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "parada")
@Getter @Setter
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "codPara")
    private Long id;

    @Column (name = "paradaNome")
    private String paradaNome;

    @Column (name = "posicao")
    private Integer posicao;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    @JsonIgnore
    private Rota rota;

    @ManyToOne
    @JoinColumn(name = "onibus_id")
    @JsonIgnore
    private Onibus onibus;

}

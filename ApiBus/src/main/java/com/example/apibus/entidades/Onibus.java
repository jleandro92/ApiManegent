package com.example.apibus.entidades;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "onibus")
@Getter @Setter
public class Onibus {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "codBus")
    private Long Id;

    @Column (name = "busNum")
    private String numOnibus;

    @Column (name = "horaSaida")
    private String horaSaida;

    @Column (name = "horaChegada")
    private String horaChegada;//hora prevista da chegada ao destino

    @Column (name = "valorLinha")
    private Float  valorLinha;

    @Column(name = "acess")
    private String acessibilidade;

    @Column(name = "week")
    private BigInteger week;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    @JsonIgnore
    private Rota rota;
    
    @OneToMany(mappedBy = "onibus")
    private List<Parada>paradas;

}


package com.example.apibus.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "tb_cidade")
@Getter @Setter
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "codCity")
    private Long id;

    @Column(name = "nomeCidade")
    private String nomeCidade;

    @OneToMany(mappedBy = "cidade")
    private List<Parada> paradas;

    
}

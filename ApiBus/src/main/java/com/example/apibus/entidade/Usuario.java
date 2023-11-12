package com.example.apibus.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//NewTable
@Entity
@Table(name = "usuarios")
@Getter @Setter
public class Usuario   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;

    @Email
    @Column (name = "email")
    private String email;

    @Column (name = "nomeUser")
    @Size(min = 3, max = 20, message = "Usuario deve informa um nome real")
    private String nomeUser;


    @Column (name = "idGoogle")
    private String idGoogle;

    @Column(name = "nivel")
    private Integer nivel;


    public Usuario() {
        this.nivel = 1; // Definindo o valor fixo para o nível
    }
    
     @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rotas",
      joinColumns = @JoinColumn(name = "usuario_id", 
referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "rota_id",
referencedColumnName = "id"))
    @JsonIgnore
    private List<Rota> rotas;

}

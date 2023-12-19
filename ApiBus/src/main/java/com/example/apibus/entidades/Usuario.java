package com.example.apibus.entidades;

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
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_seq_generator")
    @SequenceGenerator(name = "usuarios_seq_generator", sequenceName = "usuarios_SEQ", allocationSize = 1)
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

    // Construtor padrão
    public Usuario() {
        this.nivel = 1;
    }

     @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rotas",
      joinColumns = @JoinColumn(name = "usuario_id", 
referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "rota_id",
referencedColumnName = "id"))
    @JsonIgnore
    private List<Rota> rotas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.nivel == 100){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

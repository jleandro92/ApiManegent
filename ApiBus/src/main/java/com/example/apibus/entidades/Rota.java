package com.example.apibus.entidades;

import java.util.List;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table (name = "rotas")
@Getter @Setter
public class Rota {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column (name = "id")
  private Long id;

  @Column (name = "nomeRota")
  private String nomeRota;

  @OneToMany(mappedBy = "rota")
  private List<Onibus> onibus;

  @OneToMany(mappedBy = "rota")
  private List<Parada> paradas;

 
    @ManyToMany(mappedBy = "rotas")
    private List<Usuario> usuarios;

  public void removerParada(Long paradaId) {
    Parada paradaRemover = null;
    for (Parada parada : paradas) {
      if (parada.getId().equals(paradaId)) {
        paradaRemover = parada;
        break;
      }
    }
    if (paradaRemover != null) {
      paradas.remove(paradaRemover);
    }
  }
  public void removerOnibus(Long onibusId) {
    Onibus onibusRemover = null;
    for (Onibus onibus : onibus){
      if (onibus.getId().equals(onibusId)){
        onibusRemover = onibus;
        break;
      }
    }
    if (onibusRemover != null){
      onibus.remove(onibusRemover);
    }
  }

    public String getDetalhes() {
      return nomeRota;
    }
}

 
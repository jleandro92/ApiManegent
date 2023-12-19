package com.example.apibus.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.apibus.entidades.Parada;

@RepositoryRestResource(collectionResourceRel = "parada", path = "parada" )
@CrossOrigin(origins = "*")
public interface ParadaRepository extends JpaRepository<Parada, Long>{

    List<Parada> findByPosicao(@Param("posicao") Integer name);
    
}

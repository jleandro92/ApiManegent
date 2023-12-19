package com.example.apibus.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.example.apibus.entidades.Onibus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "onibus", path = "onibus")
@CrossOrigin(origins = "*")
public interface OnibusRepository extends JpaRepository <Onibus, Long> {
    
    List<Onibus> findBynumOnibus (@Param("numOnibus") String name);
}

package com.example.apibus.repositorys;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.apibus.entidade.Cidade;


@RepositoryRestResource(collectionResourceRel = "cidade", path = "cidade")
@CrossOrigin(origins = "*")
public interface CidadeRepository extends JpaRepository <Cidade,Long>{

    List<Cidade>findBynomeCidade(@Param("nomeCidade") String name);


}

package com.example.apibus.repositorys;


import com.example.apibus.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
//NewTable
@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
@CrossOrigin(origins = "*")
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findBynomeUser (@Param("nomeUser") String name);

}

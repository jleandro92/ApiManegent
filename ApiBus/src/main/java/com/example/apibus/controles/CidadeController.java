package com.example.apibus.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apibus.entidade.Cidade;
import com.example.apibus.repositorys.CidadeRepository;
import com.example.apibus.servicos.CidadeService;

@RestController
@RequestMapping("/")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;
    
    @Autowired
    private CidadeRepository cidadeRepository;


    @GetMapping("/cidade/")
    public List<Cidade>listCidades(){
        List<Cidade>listCidades = cidadeRepository.findAll();

        return listCidades;
    }

    @GetMapping("/cidade/{cidadeId}/parada")
    public List<Cidade>listCid(){
        List<Cidade>listCid = cidadeRepository.findAll();

        return listCid;
    }

    @PutMapping("cidade/{cidadeId}/addParada/{paradaId}")
    public Cidade addParada(@PathVariable("cidadeId") Long cidadeId,
    @PathVariable("paradaId") Long paradaId){
        return cidadeService.addParada(cidadeId,paradaId);
    }
    
    
}

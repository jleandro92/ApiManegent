package com.example.apibus.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apibus.entidade.Cidade;
import com.example.apibus.entidade.Parada;
import com.example.apibus.repositorys.CidadeRepository;
import com.example.apibus.repositorys.ParadaRepository;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private ParadaRepository paradaRepository;

    @Override
    public Cidade addParada(Long cidadeId, Long paradaId){
        Cidade cidade = cidadeRepository.findById(cidadeId).get();
        Parada parada = paradaRepository.findById(paradaId).get();
        parada.setCidade(cidade);
        paradaRepository.save(parada);
        return cidade;
    }
}

package com.example.apibus.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apibus.entidade.Onibus;
import com.example.apibus.entidade.Parada;
import com.example.apibus.entidade.Rota;
import com.example.apibus.repositorys.OnibusRepository;
import com.example.apibus.repositorys.ParadaRepository;
import com.example.apibus.repositorys.RotaRepository;

@Service
public class RotaServiceImpl implements RotaService {
    
    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private OnibusRepository onibusRepository;

    @Autowired
    private ParadaRepository paradaRepository;


     @Override
    public Rota addParada(Long rotaId, Long paradaId) {
        Rota rota = rotaRepository.findById(rotaId).get();
        Parada parada = paradaRepository.findById(paradaId).get();
        parada.setRota(rota);
        paradaRepository.save(parada);
        return rota;
    }

     @Override
    public Rota addOnibus(Long rotaId, Long onibusId){
        Rota rota = rotaRepository.findById(rotaId).get();
        Onibus onibus = onibusRepository.findById(onibusId).get();
        onibus.setRota(rota);
        onibusRepository.save(onibus);
        return rota;
    }
}

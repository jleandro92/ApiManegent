package com.example.apibus.servicos;

import com.example.apibus.entidades.Onibus;
import com.example.apibus.entidades.Parada;
import com.example.apibus.repositorys.OnibusRepository;
import com.example.apibus.repositorys.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnibusServiceImpl implements OnibusService{

    @Autowired
    private ParadaRepository paradaRepository;

    @Autowired
    private OnibusRepository onibusRepository;

    @Override
    public Onibus addParadaBus(Long onibusId, Long paradaId){
        Onibus onibus = onibusRepository.findById(onibusId).get();
        Parada parada = paradaRepository.findById(paradaId).get();
        parada.setOnibus(onibus);
        paradaRepository.save(parada);

        return onibus;
    }

}

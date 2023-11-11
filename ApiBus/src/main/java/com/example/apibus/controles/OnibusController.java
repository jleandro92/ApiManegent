package com.example.apibus.controles;

import com.example.apibus.entidade.Onibus;
import com.example.apibus.entidade.OnibusForm;
import com.example.apibus.entidade.Rota;
import com.example.apibus.repositorys.OnibusRepository;
import com.example.apibus.repositorys.RotaRepository;
import com.example.apibus.servicos.OnibusService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OnibusController {

    @Autowired
    private OnibusService onibusService;

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private OnibusRepository onibusRepository;

    @GetMapping("/onibus/")
    public List<Onibus> listBus(){
        List<Onibus> listBus = onibusRepository.findAll();

        return listBus;
    }
    @PostMapping("/onibus/")
    public Object saveBus(@RequestBody OnibusForm ob){
        Onibus onibus = new Onibus();

        onibus.setAcessibilidade(ob.getAcessibilidade());
        onibus.setHoraChegada(ob.getHoraChegada());
        onibus.setHoraSaida(ob.getHoraSaida());
        onibus.setValorLinha(ob.getValorLinha());
        onibus.setNumOnibus(ob.getNumOnibus());
        onibus.setWeek(ob.getWeek());

        if(rotaRepository.existsById(ob.getRota_id())){
            Rota rota = rotaRepository.findById(ob.getRota_id()).get();
            onibus.setRota(rota);
        }


        return onibusRepository.save(onibus);
    }

    @PutMapping("onibus/{onibusId}/addParadaBus/{paradaId}")
    public Onibus addParadaBus (@PathVariable("onibusId") Long onibusId,
                                @PathVariable("paradaId") Long paradaId){
        return onibusService.addParadaBus(onibusId,paradaId);
    }
}

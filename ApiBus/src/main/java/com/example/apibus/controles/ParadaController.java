package com.example.apibus.controles;

import com.example.apibus.entidade.Parada;
import com.example.apibus.entidade.ParadaForm;
import com.example.apibus.entidade.Rota;
import com.example.apibus.repositorys.ParadaRepository;
import com.example.apibus.repositorys.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ParadaController {

    @Autowired
    private ParadaRepository paradaRepository;

    @Autowired
    private RotaRepository rotaRepository;

    @GetMapping("/parada/")
    public List<Parada> paradaList(){
        List<Parada> paradaList = paradaRepository.findAll();

        return paradaList;
    }

    @PostMapping("/parada/")
    public Object saveParada(@RequestBody ParadaForm pf){
        Parada parada = new Parada();

        parada.setParadaNome(pf.getParadaNome());
        parada.setPosicao(pf.getPosicao());

        if(rotaRepository.existsById(pf.getRota_id())){
            Rota rota = rotaRepository.findById(pf.getRota_id()).get();
            parada.setRota(rota);
        }



        return paradaRepository.save(parada);
    }
}

package com.example.apibus.controles;


import com.example.apibus.entidade.Rota;
import com.example.apibus.repositorys.RotaRepository;
import com.example.apibus.servicos.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RotaController {
    
    @Autowired
    private RotaService rotaService;

     @Autowired
    private RotaRepository rotaRepository;

      @GetMapping("/rota/")
      public List<Rota> listRotas() {

         List<Rota> listRotas = rotaRepository.findAll();

         return listRotas;
      }

    @PutMapping("rota/{rotaId}/addParada/{paradaId}")
    public Rota addParada (@PathVariable("rotaId") Long rotaId,
     @PathVariable("paradaId") Long paradaId){

        return rotaService.addParada(rotaId,paradaId);

     }
     @PutMapping("rota/{rotaId}/addOnibus/{onibusId}")
     public Rota addOnibus (@PathVariable("rotaId") Long rotaId, @PathVariable("onibusId") Long onibusId){

        return rotaService.addOnibus(rotaId, onibusId);
      }
}

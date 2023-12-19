package com.example.apibus.controles;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apibus.entidades.Rota;
import com.example.apibus.repositorys.RotaRepository;
import com.example.apibus.servicos.RotaService;

@RestController
@CrossOrigin(origins = "*")
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
        public Rota addOnibus (@PathVariable("rotaId") Long rotaId,
        @PathVariable("onibusId") Long onibusId){

        return rotaService.addOnibus(rotaId, onibusId);
   }

    @PutMapping("/rota/{rotaId}/removeParada/{paradaId}")
    public Rota removeParada(@PathVariable("rotaId") Long rotaId,
                             @PathVariable("paradaId") Long paradaId) {
        return rotaService.removeParada(rotaId, paradaId);
    }

    @PutMapping("rota/{rotaId}/removeOnibus/{onibusId}")
    public Rota removeOnibus(@PathVariable("rotaId") Long rotaId,
                             @PathVariable("onibusId") Long onibusId){
        return rotaService.removeOnibus(rotaId, onibusId);
    }
    @DeleteMapping("/rota/{rotaId}")
    public ResponseEntity<String> deleteRota(@PathVariable("rotaId") Long rotaId) {
        Rota rota = rotaRepository.findById(rotaId).orElse(null);

        if (rota != null) {
            rotaRepository.delete(rota);
            return new ResponseEntity<>("Rota deletada com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rota não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

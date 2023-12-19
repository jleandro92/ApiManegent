package com.example.apibus.servicos;

import com.example.apibus.entidades.Usuario;
import com.ms.notific.services.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.apibus.entidades.Onibus;
import com.example.apibus.entidades.Parada;
import com.example.apibus.entidades.Rota;
import com.example.apibus.repositorys.OnibusRepository;
import com.example.apibus.repositorys.ParadaRepository;
import com.example.apibus.repositorys.RotaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RotaServiceImpl implements RotaService {
    
    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private OnibusRepository onibusRepository;

    @Autowired
    private ParadaRepository paradaRepository;

    private final NotificacaoService notificacaoService;

    @Autowired
    @Lazy
    public RotaServiceImpl(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Bean
    public NotificacaoService notificacaoService() {
        return new NotificacaoService();
    }


    

    @Transactional
    @Override
    public Rota addParada(Long rotaId, Long paradaId) {
        Rota rota = rotaRepository.findById(rotaId).get();
        Parada parada = paradaRepository.findById(paradaId).get();
        parada.setRota(rota);
        paradaRepository.save(parada);

        notificacaoService.notificaAlteracaoRota((Usuario) rota.getUsuarios(), rota, "Nova parada adicionada à rota!");
        return rota;
    }

    @Transactional
    @Override
    public Rota addOnibus(Long rotaId, Long onibusId){
        Rota rota = rotaRepository.findById(rotaId).get();
        Onibus onibus = onibusRepository.findById(onibusId).get();
        onibus.setRota(rota);
        onibusRepository.save(onibus);

        notificacaoService.notificaAlteracaoRota((Usuario) rota.getUsuarios(), rota, "Novo ônibus adicionado à rota!");
        return rota;
    }

    @Transactional
    public Rota removeParada(Long rotaId, Long paradaId) {
        Rota rota = rotaRepository.findById(rotaId).orElse(null);
        if (rota != null) {
            rota.removerParada(paradaId);
            rotaRepository.save(rota);

            notificacaoService.notificaAlteracaoRota((Usuario) rota.getUsuarios(), rota, "Parada removida da rota!");

        }
        return rota;
    }

    @Transactional
    public Rota removeOnibus(Long rotaId, Long onibusId){
        Rota rota = rotaRepository.findById(rotaId).orElse(null);
        if(rota != null){
            rota.removerOnibus(onibusId);
            rotaRepository.save(rota);

            // Notificar usuários sobre a remoção de ônibus
            notificacaoService.notificaAlteracaoRota((Usuario) rota.getUsuarios(), rota, "Ônibus removido da rota!");
        }
        return rota;
    }
}

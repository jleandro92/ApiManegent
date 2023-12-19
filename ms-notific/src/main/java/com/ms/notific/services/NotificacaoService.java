package com.ms.notific.services;

import com.example.apibus.entidades.Rota;
import com.example.apibus.entidades.Usuario;
import com.ms.notific.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    @Autowired
    private EmailService emailService;

    public void notificaAlteracaoRota(Usuario usuario, Rota rota, String mensagem) {
        try {
            String assunto = "Alteração na rota favorita";
            String corpo = mensagem + "\nDetalhes da rota: " + rota.getDetalhes();

            EmailModel emailModel = new EmailModel();
            emailModel.setEmailFrom("jose.dev0802@gmail.com");
            emailModel.setEmailTo(usuario.getEmail());
            emailModel.setSubject(assunto);
            emailModel.setText(corpo);

            emailService.sendEmailAsync(emailModel);
        } catch (Exception e) {
            System.out.println("Erro ao enviar email de notificação de alteração de rota.");
        }
    }
}
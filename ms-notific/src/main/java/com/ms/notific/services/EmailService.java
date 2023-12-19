package com.ms.notific.services;

import com.ms.notific.enums.StatusEmail;
import com.ms.notific.models.EmailModel;
import com.ms.notific.repositories.EmailRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmailService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            rabbitTemplate.convertAndSend("fila-email", emailModel);
            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){

            emailModel.setStatusEmail(StatusEmail.ERROR);

        }finally{
            return emailRepository.save(emailModel);
        }
    }

    public Page<EmailModel> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

    public Optional<EmailModel> findById(UUID emailId) {
        return emailRepository.findById(emailId);
    }

    @Async
    public CompletableFuture<Void> sendEmailAsync(EmailModel emailModel) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            
            sendEmailAsync(emailModel);

            rabbitTemplate.convertAndSend("fila-email", emailModel);
            emailModel.setStatusEmail(StatusEmail.SENT);
            return CompletableFuture.completedFuture(null); // Indica conclusão com sucesso
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e); // Indica falha no envio do e-mail
        }
    }

}

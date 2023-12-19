package com.ms.notific.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ms.notific.services.NotificacaoService;

@Configuration
@ComponentScan(basePackages = "com.ms.notific")
public class EmailConfig {

    @Bean
    public NotificacaoService notificacaoService() {
        return new NotificacaoService();
    }
    // Pode adicionar configurações adicionais aqui, se necessário
}

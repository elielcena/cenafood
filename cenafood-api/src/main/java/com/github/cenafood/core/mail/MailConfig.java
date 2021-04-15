package com.github.cenafood.core.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.cenafood.domain.service.SendMailService;

/**
 * @author elielcena
 *
 */
@Configuration
public class MailConfig {

    @Bean
    public SendMailService sendMailService(MailProperties mailProperties) {
        return SendMailService.getImplementation(mailProperties.getImplementation());
    }

}

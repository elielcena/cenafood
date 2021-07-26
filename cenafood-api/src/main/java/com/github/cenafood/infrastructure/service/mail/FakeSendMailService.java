package com.github.cenafood.infrastructure.service.mail;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.cenafood.domain.service.SendMailService;
import com.github.cenafood.infrastructure.service.exception.MailException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author elielcena
 *
 */
@Slf4j
public class FakeSendMailService implements SendMailService {

    private static final String ERROR_SEND_MAIL = "Unable to send mail";

    @Autowired
    private MailProcessTemplate mailProcessTemplate;

    @Override
    public void send(Message message) {
        try {
            String body = mailProcessTemplate.processTemplate(message);
            log.info("[FAKE MAIL] TO: {}\n{}", message.getTo(), body);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailException(ERROR_SEND_MAIL, e);
        }
    }

}

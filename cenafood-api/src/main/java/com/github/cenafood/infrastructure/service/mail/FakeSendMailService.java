package com.github.cenafood.infrastructure.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.github.cenafood.domain.service.SendMailService;
import com.github.cenafood.infrastructure.service.exception.MailException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elielcena
 *
 */
@Slf4j
@Service
public class FakeSendMailService implements SendMailService {

    private static final String ERROR_SEND_MAIL = "Unable to send mail";

    private static final String ERROR_PROCESS_TEMPLATE_MAIL = "Unable to process template mail";

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void send(Message message) {
        try {
            String body = processTemplate(message);
            log.info("[FAKE MAIL] TO: {}\n{}", message.getTo(), body);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailException(ERROR_SEND_MAIL, e);
        }
    }

    private String processTemplate(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailException(ERROR_PROCESS_TEMPLATE_MAIL, e);
        }
    }

}

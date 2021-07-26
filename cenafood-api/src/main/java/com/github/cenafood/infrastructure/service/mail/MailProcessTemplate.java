package com.github.cenafood.infrastructure.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.github.cenafood.domain.service.SendMailService.Message;
import com.github.cenafood.infrastructure.service.exception.MailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author elielcena
 *
 */
@Component
public class MailProcessTemplate {

    private static final String ERROR_PROCESS_TEMPLATE_MAIL = "Unable to process template mail";

    @Autowired
    private Configuration freemarkerConfig;

    protected String processTemplate(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailException(ERROR_PROCESS_TEMPLATE_MAIL, e);
        }
    }

}

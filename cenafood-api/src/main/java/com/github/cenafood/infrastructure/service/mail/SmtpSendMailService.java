package com.github.cenafood.infrastructure.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.github.cenafood.core.mail.MailProperties;
import com.github.cenafood.domain.service.SendMailService;
import com.github.cenafood.infrastructure.service.exception.MailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author elielcena
 *
 */
@Service
public class SmtpSendMailService implements SendMailService {

    private static final String ERROR_SEND_MAIL = "Unable to send mail";

    private static final String ERROR_PROCESS_TEMPLATE_MAIL = "Unable to process template mail";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = mimeMessageBuilder(message);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailException(ERROR_SEND_MAIL, e);
        }
    }

    protected MimeMessage mimeMessageBuilder(Message mensagem) throws MessagingException {
        String body = processTemplate(mensagem);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(mailProperties.getFrom());
        helper.setTo(mensagem.getTo().toArray(new String[0]));
        helper.setSubject(mensagem.getSubject());
        helper.setText(body, true);

        return mimeMessage;
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

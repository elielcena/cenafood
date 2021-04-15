package com.github.cenafood.infrastructure.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.github.cenafood.core.mail.MailProperties;

/**
 * @author elielcena
 *
 */
@Service
public class SandboxSendMailService extends SmtpSendMailService {

    @Autowired
    private MailProperties mailProperties;

    @Override
    protected MimeMessage mimeMessageBuilder(Message mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.mimeMessageBuilder(mensagem);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(mailProperties.getSandbox().getTo());

        return mimeMessage;
    }

}

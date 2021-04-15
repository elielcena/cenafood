package com.github.cenafood.domain.service;

import java.util.Map;
import java.util.Set;

import com.github.cenafood.core.mail.MailProperties.Implementation;
import com.github.cenafood.infrastructure.service.mail.FakeSendMailService;
import com.github.cenafood.infrastructure.service.mail.SandboxSendMailService;
import com.github.cenafood.infrastructure.service.mail.SmtpSendMailService;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

/**
 * @author elielcena
 *
 */
public interface SendMailService {

    void send(Message message);

    static SendMailService getImplementation(Implementation implementation) {
        switch (implementation) {
            case FAKE:
                return new FakeSendMailService();
            case SMTP:
                return new SmtpSendMailService();
            case SANDBOX:
                return new SandboxSendMailService();
            default:
                return null;
        }
    }

    @Builder
    @Data
    class Message {

        @Singular("to")
        @NonNull
        private Set<String> to;

        @NonNull
        private String subject;

        @NonNull
        private String body;

        @Singular
        private Map<String, Object> variables;

    }
}

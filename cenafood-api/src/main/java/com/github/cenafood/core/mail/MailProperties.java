package com.github.cenafood.core.mail;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author elielcena
 *
 */
@Validated
@Data
@Component
@ConfigurationProperties("cenafood.mail")
public class MailProperties {

    @NotNull
    private String from;

    private Sandbox sandbox = new Sandbox();

    private Implementation implementation = Implementation.FAKE;

    public enum Implementation {
        SMTP,
        FAKE,
        SANDBOX
    }

    @Data
    public class Sandbox {

        private String to;

    }

}

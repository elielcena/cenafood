package com.github.cenafood.core.auth;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
@Component
@Data
@ConfigurationProperties("cenafood.jwt.keystore")
public class JwtKeyStoreProperties {

    @NotBlank
    private String path;

    @NotBlank
    private String password;

    @NotBlank
    private String keypairAlias;

}

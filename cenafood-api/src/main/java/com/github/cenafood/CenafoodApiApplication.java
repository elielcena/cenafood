package com.github.cenafood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.cenafood.core.io.Base64ProtocolResolver;

@SpringBootApplication
public class CenafoodApiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        var app = new SpringApplication(CenafoodApiApplication.class);

        app.addListeners(new Base64ProtocolResolver());

        app.run(args);
    }

}

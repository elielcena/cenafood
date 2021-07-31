package com.github.cenafood.core.io;

import java.util.Base64;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author elielcena
 *
 */
public class Base64ProtocolResolver implements ProtocolResolver, ApplicationListener<ApplicationContextInitializedEvent> {

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith("base64:")) {
            byte[] byteArray = Base64.getDecoder().decode(location.substring(7));

            return new ByteArrayResource(byteArray);
        }

        return null;
    }

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        event.getApplicationContext().addProtocolResolver(this);
    }

}
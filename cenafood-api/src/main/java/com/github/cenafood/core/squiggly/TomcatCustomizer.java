package com.github.cenafood.core.squiggly;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author elielcena
 *         {@link https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666
 *         }
 *
 */
@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]"));
	}

}

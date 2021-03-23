package com.github.cenafood.core.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author elielcena
 *
 */

@Configuration
public class MessageConfig {

	@Bean
	public ResourceBundleMessageSource messageSource() {

		var source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		source.setDefaultEncoding("UTF-8");

		return source;
	}

}

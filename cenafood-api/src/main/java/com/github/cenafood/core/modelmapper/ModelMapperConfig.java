package com.github.cenafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.cenafood.api.v1.model.request.OrderRequestDTO;
import com.github.cenafood.domain.model.OrderItem;

/**
 * @author elielcena
 *
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(OrderRequestDTO.OrderItemAbstractRequestDTO.class, OrderItem.class)
				.addMappings(mapper -> mapper.skip(OrderItem::setId));

		return modelMapper;
	}

}

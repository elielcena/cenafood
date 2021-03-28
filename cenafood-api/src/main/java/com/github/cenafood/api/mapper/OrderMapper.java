package com.github.cenafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.OrderRequestDTO;
import com.github.cenafood.api.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.api.model.response.OrderCreatedResponseDTO;
import com.github.cenafood.api.model.response.OrderResponseDTO;
import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Component
public class OrderMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrderResponseDTO toDTO(Order order) {
		return modelMapper.map(order, OrderResponseDTO.class);
	}

	public List<OrderResponseDTO> toCollectionDTO(Collection<Order> order) {
		return order.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

	public OrderCreatedResponseDTO toCreatedDTO(Order order) {
		return modelMapper.map(order, OrderCreatedResponseDTO.class);
	}

	public OrderAbstractResponseDTO toAbstractDTO(Order order) {
		return modelMapper.map(order, OrderAbstractResponseDTO.class);
	}

	public List<OrderAbstractResponseDTO> toAbstractCollectionDTO(Collection<Order> order) {
		return order.stream().map(rest -> toAbstractDTO(rest)).collect(Collectors.toList());
	}

	public Order toDomainEntity(OrderRequestDTO order) {
		return modelMapper.map(order, Order.class);
	}

	public void copyToDomainEntity(OrderRequestDTO orderRequest, Order order) {
		modelMapper.map(orderRequest, order);
	}

}

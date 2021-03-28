package com.github.cenafood.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.OrderMapper;
import com.github.cenafood.api.model.request.OrderRequestDTO;
import com.github.cenafood.api.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.api.model.response.OrderCreatedResponseDTO;
import com.github.cenafood.api.model.response.OrderResponseDTO;
import com.github.cenafood.domain.filter.OrderFilter;
import com.github.cenafood.domain.model.Order;
import com.github.cenafood.domain.service.OrderService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper mapper;

	@GetMapping
	public List<OrderAbstractResponseDTO> findAllWithFilter(OrderFilter filter) {
		return mapper.toAbstractCollectionDTO(orderService.findAll(filter));
	}

	@GetMapping("/{code}")
	public OrderResponseDTO findById(@PathVariable UUID code) {
		return mapper.toDTO(orderService.findByCode(code));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderCreatedResponseDTO generate(@Valid @RequestBody OrderRequestDTO orderRequest) {
		Order order = orderService.generate(mapper.toDomainEntity(orderRequest));

		return mapper.toCreatedDTO(order);
	}

	@PutMapping("/{code}/confirmation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirm(@PathVariable UUID code) {
		orderService.confirm(code);
	}

	@PutMapping("/{code}/delivery")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delivery(@PathVariable UUID code) {
		orderService.delivery(code);
	}

	@PutMapping("/{code}/cancelation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancel(@PathVariable UUID code) {
		orderService.cancel(code);
	}

}

package com.github.cenafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.github.cenafood.api.openapi.controller.OrderControllerOpenApi;
import com.github.cenafood.domain.filter.OrderFilter;
import com.github.cenafood.domain.model.Order;
import com.github.cenafood.domain.service.OrderService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderControllerOpenApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper mapper;

    @GetMapping
    public Page<OrderAbstractResponseDTO> findAllWithFilter(OrderFilter filter,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Order> orderPage = orderService.findAllWithFilterAndPage(filter, pageable);

        List<OrderAbstractResponseDTO> orderAbstractDTO = mapper.toAbstractCollectionDTO(orderPage.getContent());

        return new PageImpl<>(orderAbstractDTO, pageable, orderPage.getTotalElements());
    }

    @GetMapping("/{code}")
    public OrderResponseDTO findByCode(@PathVariable String code) {
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
    public void confirm(@PathVariable String code) {
        orderService.confirm(code);
    }

    @PutMapping("/{code}/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivery(@PathVariable String code) {
        orderService.delivery(code);
    }

    @PutMapping("/{code}/cancelation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String code) {
        orderService.cancel(code);
    }

}

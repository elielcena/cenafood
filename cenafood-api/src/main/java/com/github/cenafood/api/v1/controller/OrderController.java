package com.github.cenafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.mapper.OrderAbstractMapper;
import com.github.cenafood.api.v1.mapper.OrderCreatedMapper;
import com.github.cenafood.api.v1.mapper.OrderMapper;
import com.github.cenafood.api.v1.model.request.OrderRequestDTO;
import com.github.cenafood.api.v1.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.api.v1.model.response.OrderCreatedResponseDTO;
import com.github.cenafood.api.v1.model.response.OrderResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.OrderControllerOpenApi;
import com.github.cenafood.core.data.PageWrapper;
import com.github.cenafood.core.security.anotation.CheckSecurity;
import com.github.cenafood.domain.filter.OrderFilter;
import com.github.cenafood.domain.model.Order;
import com.github.cenafood.domain.service.OrderService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderControllerOpenApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderCreatedMapper orderCreatedMapper;

    @Autowired
    private OrderAbstractMapper orderAbstractMapper;

    @Autowired
    private PagedResourcesAssembler<Order> pagedResourcesAssembler;

    @CheckSecurity.Orders.Consult
    @GetMapping
    public PagedModel<OrderAbstractResponseDTO> findAllWithFilter(OrderFilter filter,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Order> orderPage = orderService.findAllWithFilterAndPage(filter, pageable);

        orderPage = new PageWrapper<Order>(orderPage, pageable);

        return pagedResourcesAssembler.toModel(orderPage, orderAbstractMapper);
    }

    @CheckSecurity.Orders.Find
    @GetMapping("/{code}")
    public OrderResponseDTO findByCode(@PathVariable String code) {
        return mapper.toModel(orderService.findByCode(code));
    }

    @CheckSecurity.Orders.Save
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreatedResponseDTO generate(@Valid @RequestBody OrderRequestDTO orderRequest) {
        Order order = orderService.generate(mapper.toDomainEntity(orderRequest));

        return orderCreatedMapper.toModel(order);
    }

    @CheckSecurity.Orders.Manage
    @PutMapping("/{code}/confirmation")
    public ResponseEntity<Void> confirm(@PathVariable String code) {
        orderService.confirm(code);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Orders.Manage
    @PutMapping("/{code}/delivery")
    public ResponseEntity<Void> delivery(@PathVariable String code) {
        orderService.delivery(code);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Orders.Manage
    @PutMapping("/{code}/cancelation")
    public ResponseEntity<Void> cancel(@PathVariable String code) {
        orderService.cancel(code);
        return ResponseEntity.noContent().build();
    }

}

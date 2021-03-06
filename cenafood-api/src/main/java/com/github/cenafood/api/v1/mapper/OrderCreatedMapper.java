package com.github.cenafood.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.OrderController;
import com.github.cenafood.api.v1.model.response.OrderCreatedResponseDTO;
import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Component
public class OrderCreatedMapper extends RepresentationModelAssemblerSupport<Order, OrderCreatedResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    public OrderCreatedMapper() {
        super(OrderController.class, OrderCreatedResponseDTO.class);
    }

    @Override
    public OrderCreatedResponseDTO toModel(Order order) {
        OrderCreatedResponseDTO orderCreated = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderCreated);

        return orderCreated.add(cenaLinks.linkToOrders());
    }

}

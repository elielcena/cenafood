package com.github.cenafood.api.mapper;

import static org.apache.commons.lang3.BooleanUtils.isTrue;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.CenaLinks;
import com.github.cenafood.api.controller.OrderController;
import com.github.cenafood.api.model.request.OrderRequestDTO;
import com.github.cenafood.api.model.response.OrderResponseDTO;
import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Component
public class OrderMapper extends RepresentationModelAssemblerSupport<Order, OrderResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    public OrderMapper() {
        super(OrderController.class, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO toModel(Order order) {
        OrderResponseDTO orderResponse = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderResponse);

        orderResponse.add(cenaLinks.linkToOrders());

        if (isTrue(order.canBeConfirmed()))
            orderResponse.add(cenaLinks.linkToCorfirmOrder(order.getCode()));

        if (isTrue(order.canBeDelivered()))
            orderResponse.add(cenaLinks.linkToDeliveryOrder(order.getCode()));

        if (isTrue(order.canBeCanceled()))
            orderResponse.add(cenaLinks.linkToCancelOrder(order.getCode()));

        orderResponse.getRestaurant().add(cenaLinks.linkToRestaurant(orderResponse.getRestaurant().getId()));
        orderResponse.getCustomer().add(cenaLinks.linkToUser(orderResponse.getCustomer().getId()));

        return orderResponse;
    }

    public Order toDomainEntity(OrderRequestDTO order) {
        return modelMapper.map(order, Order.class);
    }

    public void copyToDomainEntity(OrderRequestDTO orderRequest, Order order) {
        modelMapper.map(orderRequest, order);
    }

}

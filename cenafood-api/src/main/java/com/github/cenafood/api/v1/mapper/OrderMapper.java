package com.github.cenafood.api.v1.mapper;

import static org.apache.commons.lang3.BooleanUtils.isTrue;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.OrderController;
import com.github.cenafood.api.v1.model.request.OrderRequestDTO;
import com.github.cenafood.api.v1.model.response.OrderResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
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

    @Autowired
    private SecurityUtil securityUtil;

    public OrderMapper() {
        super(OrderController.class, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO toModel(Order order) {
        OrderResponseDTO orderResponse = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderResponse);

        if (isTrue(securityUtil.manageOrder(order.getCode()))) {
            if (isTrue(order.canBeConfirmed()))
                orderResponse.add(cenaLinks.linkToCorfirmOrder(order.getCode()));

            if (isTrue(order.canBeDelivered()))
                orderResponse.add(cenaLinks.linkToDeliveryOrder(order.getCode()));

            if (isTrue(order.canBeCanceled()))
                orderResponse.add(cenaLinks.linkToCancelOrder(order.getCode()));
        }

        if (isTrue(securityUtil.noPreAuthorizeRead())) {
            orderResponse.add(cenaLinks.linkToOrders());
            orderResponse.getRestaurant().add(cenaLinks.linkToRestaurant(orderResponse.getRestaurant().getId()).withRel("restaurant"));
            orderResponse.getRestaurant().getKitchen().add(cenaLinks.linkToKitchen(orderResponse.getRestaurant().getKitchen().getId()).withRel("kitchen"));
            orderResponse.getPaymentMethod().add(cenaLinks.linkToPaymentMethod(order.getPaymentMethod().getId()));
            orderResponse.getAddress().getCity().add(cenaLinks.linkToCity(orderResponse.getAddress().getCity().getId()).withRel("city"));
            orderResponse.getAddress().getCity().getState()
                    .add(cenaLinks.linkToState(orderResponse.getAddress().getCity().getState().getUf()).withRel("state"));

            orderResponse.getOrderItems().forEach(item -> {
                item.add(cenaLinks.linkToProduct(order.getRestaurant().getId(), item.getId()).withRel("orderitems"));
            });
        }

        if (isTrue(securityUtil.consultUsersRolesPermissions()))
            orderResponse.getCustomer().add(cenaLinks.linkToUser(orderResponse.getCustomer().getId()).withRel("customer"));

        return orderResponse;
    }

    public Order toDomainEntity(OrderRequestDTO order) {
        return modelMapper.map(order, Order.class);
    }

    public void copyToDomainEntity(OrderRequestDTO orderRequest, Order order) {
        modelMapper.map(orderRequest, order);
    }

}

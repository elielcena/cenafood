package com.github.cenafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.CenaLinks;
import com.github.cenafood.api.controller.OrderController;
import com.github.cenafood.api.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Component
public class OrderAbstractMapper extends RepresentationModelAssemblerSupport<Order, OrderAbstractResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    public OrderAbstractMapper() {
        super(OrderController.class, OrderAbstractResponseDTO.class);
    }

    @Override
    public OrderAbstractResponseDTO toModel(Order order) {
        OrderAbstractResponseDTO orderAbstract = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderAbstract);

        orderAbstract.add(cenaLinks.linkToOrders());
        orderAbstract.getRestaurant().add(cenaLinks.linkToRestaurant(orderAbstract.getRestaurant().getId()));
        orderAbstract.getCustomer().add(cenaLinks.linkToUser(orderAbstract.getCustomer().getId()));

        return orderAbstract;
    }

}

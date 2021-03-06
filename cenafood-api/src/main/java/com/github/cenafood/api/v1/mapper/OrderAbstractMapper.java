package com.github.cenafood.api.v1.mapper;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.OrderController;
import com.github.cenafood.api.v1.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
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

    @Autowired
    private SecurityUtil securityUtil;

    public OrderAbstractMapper() {
        super(OrderController.class, OrderAbstractResponseDTO.class);
    }

    @Override
    public OrderAbstractResponseDTO toModel(Order order) {
        OrderAbstractResponseDTO orderAbstract = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderAbstract);

        if (BooleanUtils.isTrue(securityUtil.noPreAuthorizeRead())) {
            orderAbstract.add(cenaLinks.linkToOrders());
            orderAbstract.getRestaurant().add(cenaLinks.linkToRestaurant(orderAbstract.getRestaurant().getId()));
        }

        if (BooleanUtils.isTrue(securityUtil.consultUsersRolesPermissions()))
            orderAbstract.getCustomer().add(cenaLinks.linkToUser(orderAbstract.getCustomer().getId()));

        return orderAbstract;
    }

}

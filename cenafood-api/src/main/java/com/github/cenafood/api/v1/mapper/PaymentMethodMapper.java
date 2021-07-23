package com.github.cenafood.api.v1.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.PaymentMethodController;
import com.github.cenafood.api.v1.model.request.PaymentMethodRequestDTO;
import com.github.cenafood.api.v1.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
import com.github.cenafood.domain.model.PaymentMethod;

/**
 * @author elielcena
 *
 */
@Component
public class PaymentMethodMapper extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    @Autowired
    private SecurityUtil securityUtil;

    public PaymentMethodMapper() {
        super(PaymentMethodController.class, PaymentMethodResponseDTO.class);
    }

    @Override
    public PaymentMethodResponseDTO toModel(PaymentMethod paymentMethod) {
        PaymentMethodResponseDTO paymentMethodResponse = createModelWithId(paymentMethod.getId(), paymentMethod);

        modelMapper.map(paymentMethod, paymentMethodResponse);

        if (BooleanUtils.isTrue(securityUtil.noPreAuthorizeRead()))
            paymentMethodResponse.add(cenaLinks.linkToPaymentMethods());

        return paymentMethodResponse;
    }

    @Override
    public CollectionModel<PaymentMethodResponseDTO> toCollectionModel(Iterable<? extends PaymentMethod> entities) {
        var collection = super.toCollectionModel(entities);

        if (BooleanUtils.isTrue(securityUtil.noPreAuthorizeRead()))
            collection.add(linkTo(PaymentMethodController.class).withSelfRel());

        return collection;
    }

    public PaymentMethod toDomainEntity(PaymentMethodRequestDTO paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethod.class);
    }

    public void copyToDomainEntity(PaymentMethodRequestDTO paymentMethodRequest, PaymentMethod paymentMethod) {
        modelMapper.map(paymentMethodRequest, paymentMethod);
    }
}

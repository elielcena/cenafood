package com.github.cenafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.CenaLinks;
import com.github.cenafood.api.controller.PaymentMethodController;
import com.github.cenafood.api.model.request.PaymentMethodRequestDTO;
import com.github.cenafood.api.model.response.PaymentMethodResponseDTO;
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

    public PaymentMethodMapper() {
        super(PaymentMethodController.class, PaymentMethodResponseDTO.class);
    }

    @Override
    public PaymentMethodResponseDTO toModel(PaymentMethod paymentMethod) {
        PaymentMethodResponseDTO paymentMethodResponse = createModelWithId(paymentMethod.getId(), paymentMethod);

        modelMapper.map(paymentMethod, paymentMethodResponse);

        return paymentMethodResponse.add(cenaLinks.linkToPaymentMethods());
    }

    @Override
    public CollectionModel<PaymentMethodResponseDTO> toCollectionModel(Iterable<? extends PaymentMethod> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(PaymentMethodController.class).withSelfRel());
    }

    public PaymentMethod toDomainEntity(PaymentMethodRequestDTO paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethod.class);
    }

    public void copyToDomainEntity(PaymentMethodRequestDTO paymentMethodRequest, PaymentMethod paymentMethod) {
        modelMapper.map(paymentMethodRequest, paymentMethod);
    }
}

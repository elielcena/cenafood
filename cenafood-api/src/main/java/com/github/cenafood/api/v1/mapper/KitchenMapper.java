package com.github.cenafood.api.v1.mapper;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.KitchenController;
import com.github.cenafood.api.v1.model.request.KitchenRequestDTO;
import com.github.cenafood.api.v1.model.response.KitchenResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
import com.github.cenafood.domain.model.Kitchen;

/**
 * @author elielcena
 *
 */
@Component
public class KitchenMapper extends RepresentationModelAssemblerSupport<Kitchen, KitchenResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    @Autowired
    private SecurityUtil securityUtil;

    public KitchenMapper() {
        super(KitchenController.class, KitchenResponseDTO.class);
    }

    @Override
    public KitchenResponseDTO toModel(Kitchen object) {
        KitchenResponseDTO kitchenResponse = createModelWithId(object.getId(), object);
        modelMapper.map(object, kitchenResponse);

        if (isTrue(securityUtil.noPreAuthorizeRead())) {
            kitchenResponse.add(cenaLinks.linkToKitchens());
        }

        return kitchenResponse;
    }

    @Override
    public CollectionModel<KitchenResponseDTO> toCollectionModel(Iterable<? extends Kitchen> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(KitchenController.class).withSelfRel());
    }

    public Kitchen toDomainEntity(KitchenRequestDTO objectRequest) {
        return modelMapper.map(objectRequest, Kitchen.class);
    }

    public void copyToDomainEntity(KitchenRequestDTO object, Kitchen objectRequest) {
        modelMapper.map(object, objectRequest);
    }

}

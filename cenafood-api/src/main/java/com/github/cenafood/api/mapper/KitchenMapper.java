package com.github.cenafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.CenaLinks;
import com.github.cenafood.api.controller.KitchenController;
import com.github.cenafood.api.model.request.KitchenRequestDTO;
import com.github.cenafood.api.model.response.KitchenResponseDTO;
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

    public KitchenMapper() {
        super(KitchenController.class, KitchenResponseDTO.class);
    }

    @Override
    public KitchenResponseDTO toModel(Kitchen object) {
        KitchenResponseDTO kitchenResponse = createModelWithId(object.getId(), object);
        modelMapper.map(object, kitchenResponse);

        return kitchenResponse.add(cenaLinks.linkToKitchens());
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

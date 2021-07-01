package com.github.cenafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.CenaLinks;
import com.github.cenafood.api.controller.RoleController;
import com.github.cenafood.api.model.request.RoleRequestDTO;
import com.github.cenafood.api.model.response.RoleResponseDTO;
import com.github.cenafood.domain.model.Role;

/**
 * @author elielcena
 *
 */
@Component
public class RoleMapper extends RepresentationModelAssemblerSupport<Role, RoleResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    public RoleMapper() {
        super(RoleController.class, RoleResponseDTO.class);
    }

    @Override
    public RoleResponseDTO toModel(Role role) {
        RoleResponseDTO roleResponseDTO = createModelWithId(role.getId(), role);
        modelMapper.map(role, roleResponseDTO);

        roleResponseDTO.add(cenaLinks.linkToPermission(role.getId()).withRel("permissions"));

        return roleResponseDTO.add(cenaLinks.linkToRoles());
    }

    @Override
    public CollectionModel<RoleResponseDTO> toCollectionModel(Iterable<? extends Role> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(RoleController.class).withSelfRel());
    }

    public Role toDomainEntity(RoleRequestDTO role) {
        return modelMapper.map(role, Role.class);
    }

    public void copyToDomainEntity(RoleRequestDTO roleRequest, Role role) {
        modelMapper.map(roleRequest, role);
    }
}

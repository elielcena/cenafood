package com.github.cenafood.api.v1.mapper;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.RoleController;
import com.github.cenafood.api.v1.model.request.RoleRequestDTO;
import com.github.cenafood.api.v1.model.response.RoleResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
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

    @Autowired
    private SecurityUtil securityUtil;

    public RoleMapper() {
        super(RoleController.class, RoleResponseDTO.class);
    }

    @Override
    public RoleResponseDTO toModel(Role role) {
        RoleResponseDTO roleResponseDTO = createModelWithId(role.getId(), role);
        modelMapper.map(role, roleResponseDTO);

        if (isTrue(securityUtil.consultUsersRolesPermissions())) {
            roleResponseDTO.add(cenaLinks.linkToPermission(role.getId()).withRel("permissions"));

            roleResponseDTO.add(cenaLinks.linkToRoles());
        }

        return roleResponseDTO;
    }

    @Override
    public CollectionModel<RoleResponseDTO> toCollectionModel(Iterable<? extends Role> entities) {
        var collectionModel = super.toCollectionModel(entities);

        if (isTrue(securityUtil.consultUsersRolesPermissions()))
            collectionModel.add(linkTo(RoleController.class).withSelfRel());

        return collectionModel;
    }

    public Role toDomainEntity(RoleRequestDTO role) {
        return modelMapper.map(role, Role.class);
    }

    public void copyToDomainEntity(RoleRequestDTO roleRequest, Role role) {
        modelMapper.map(roleRequest, role);
    }
}

package com.github.cenafood.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.controller.RolePermissionController;
import com.github.cenafood.api.v1.model.response.PermissionResponseDTO;
import com.github.cenafood.domain.model.Permission;

/**
 * @author elielcena
 *
 */
@Component
public class PermissionMapper extends RepresentationModelAssemblerSupport<Permission, PermissionResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionMapper() {
        super(RolePermissionController.class, PermissionResponseDTO.class);
    }

    @Override
    public PermissionResponseDTO toModel(Permission permission) {
        return modelMapper.map(permission, PermissionResponseDTO.class);
    }

}

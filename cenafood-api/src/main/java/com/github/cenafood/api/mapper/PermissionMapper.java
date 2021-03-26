package com.github.cenafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.response.PermissionResponseDTO;
import com.github.cenafood.domain.model.Permission;

/**
 * @author elielcena
 *
 */
@Component
public class PermissionMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PermissionResponseDTO toDTO(Permission permission) {
		return modelMapper.map(permission, PermissionResponseDTO.class);
	}

	public List<PermissionResponseDTO> toCollectionDTO(Collection<Permission> permission) {
		return permission.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

}

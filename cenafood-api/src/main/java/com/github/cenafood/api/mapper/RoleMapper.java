package com.github.cenafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.RoleRequestDTO;
import com.github.cenafood.api.model.response.RoleResponseDTO;
import com.github.cenafood.domain.model.Role;

/**
 * @author elielcena
 *
 */
@Component
public class RoleMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RoleResponseDTO toDTO(Role role) {
		return modelMapper.map(role, RoleResponseDTO.class);
	}

	public List<RoleResponseDTO> toCollectionDTO(List<Role> role) {
		return role.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

	public Role toDomainEntity(RoleRequestDTO role) {
		return modelMapper.map(role, Role.class);
	}

	public void copyToDomainEntity(RoleRequestDTO roleRequest, Role role) {
		modelMapper.map(roleRequest, role);
	}
}

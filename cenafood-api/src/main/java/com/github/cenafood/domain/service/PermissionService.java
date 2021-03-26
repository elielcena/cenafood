package com.github.cenafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Permission;
import com.github.cenafood.domain.repository.PermissionRepository;

/**
 * @author elielcena
 *
 */
@Service
public class PermissionService {

	private static final String MSG_RESOURCE_NOT_FOUND = "There is no permission registration with code %d";

	@Autowired
	private PermissionRepository permissionRepository;

	public Permission findById(Long id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
	}

}

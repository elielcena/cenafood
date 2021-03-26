package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.EntityInUseException;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Permission;
import com.github.cenafood.domain.model.Role;
import com.github.cenafood.domain.repository.RoleRepository;

/**
 * @author elielcena
 *
 */
@Service
public class RoleService {

	private static final String MSG_RESOURCE_NOT_FOUND = "There is no role register with code %d";

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionService permissionService;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
	}

	public Role save(Role role) {
		return roleRepository.save(role);
	}

	public void delete(Long id) {
		try {
			roleRepository.delete(findById(id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException();
		}
	}

	public void addPermission(Long id, Long idPermission) {
		addOrRemovePermission(Boolean.TRUE, id, idPermission);
	}

	public void removePermission(Long id, Long idPermission) {
		addOrRemovePermission(Boolean.FALSE, id, idPermission);
	}

	private void addOrRemovePermission(Boolean isAdd, Long idRole, Long idPermission) {
		Role role = findById(idRole);
		Permission permission = permissionService.findById(idPermission);

		role.addOrRemovePermission(isAdd, permission);

		save(role);
	}

}

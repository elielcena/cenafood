package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.PermissionMapper;
import com.github.cenafood.api.model.response.PermissionResponseDTO;
import com.github.cenafood.domain.model.Role;
import com.github.cenafood.domain.service.RoleService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/roles/{id}/permissions")
public class RolePermissionController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionMapper mapper;

	@GetMapping
	public List<PermissionResponseDTO> listar(@PathVariable Long id) {
		Role role = roleService.findById(id);

		return mapper.toCollectionDTO(role.getPermissions());
	}

	@PutMapping("/{idPermission}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addPermission(@PathVariable Long id, @PathVariable Long idPermission) {
		roleService.addPermission(id, idPermission);
	}

	@DeleteMapping("/{idPermission}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePermission(@PathVariable Long id, @PathVariable Long idPermission) {
		roleService.removePermission(id, idPermission);
	}
}

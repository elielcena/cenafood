package com.github.cenafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.mapper.PermissionMapper;
import com.github.cenafood.api.v1.model.response.PermissionResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.RolePermissionControllerOpenApi;
import com.github.cenafood.domain.model.Role;
import com.github.cenafood.domain.service.RoleService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/roles/{id}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class RolePermissionController implements RolePermissionControllerOpenApi {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionMapper mapper;

    @Autowired
    private CenaLinks cenaLinks;

    @GetMapping
    public CollectionModel<PermissionResponseDTO> findAll(@PathVariable Long id) {
        Role role = roleService.findById(id);

        CollectionModel<PermissionResponseDTO> permissionResponseDTO = mapper.toCollectionModel(role.getPermissions())
                .add(cenaLinks.linkToPermission(id).withSelfRel())
                .add(cenaLinks.linkToAddPermission(id));

        permissionResponseDTO.getContent().forEach(permission -> permission.add(cenaLinks.linkToRemovePermission(id, permission.getId())));

        return permissionResponseDTO;
    }

    @PutMapping("/{idPermission}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addPermission(@PathVariable Long id, @PathVariable Long idPermission) {
        roleService.addPermission(id, idPermission);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPermission}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removePermission(@PathVariable Long id, @PathVariable Long idPermission) {
        roleService.removePermission(id, idPermission);

        return ResponseEntity.noContent().build();
    }
}

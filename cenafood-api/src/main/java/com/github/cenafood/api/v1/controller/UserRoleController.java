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
import com.github.cenafood.api.v1.mapper.RoleMapper;
import com.github.cenafood.api.v1.model.response.RoleResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.UserRoleControllerOpenApi;
import com.github.cenafood.core.security.anotation.CheckSecurity;
import com.github.cenafood.domain.model.User;
import com.github.cenafood.domain.service.UserService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/users/{id}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRoleController implements UserRoleControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private CenaLinks cenaLinks;

    @CheckSecurity.UsersRolesPermission.Consult
    @GetMapping
    public CollectionModel<RoleResponseDTO> findRolesByUser(@PathVariable Long id) {
        User user = userService.findById(id);

        CollectionModel<RoleResponseDTO> roleResponseDTO = mapper.toCollectionModel(user.getRoles())
                .add(cenaLinks.linkToAddRoleUser(id));

        roleResponseDTO.getContent().forEach(role -> role.add(cenaLinks.linkToRemoveRoleUser(id, role.getId())));

        return roleResponseDTO;
    }

    @CheckSecurity.UsersRolesPermission.Edit
    @PutMapping("/{idRole}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addRoleToUser(@PathVariable Long id, @PathVariable Long idRole) {
        userService.addRoleToUser(id, idRole);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsersRolesPermission.Edit
    @DeleteMapping("/{idRole}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeRoleToUser(@PathVariable Long id, @PathVariable Long idRole) {
        userService.removeRoleToUser(id, idRole);

        return ResponseEntity.noContent().build();
    }
}

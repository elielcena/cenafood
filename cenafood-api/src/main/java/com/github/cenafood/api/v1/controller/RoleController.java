package com.github.cenafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.mapper.RoleMapper;
import com.github.cenafood.api.v1.model.request.RoleRequestDTO;
import com.github.cenafood.api.v1.model.response.RoleResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.RoleControllerOpenApi;
import com.github.cenafood.core.security.anotation.CheckSecurity;
import com.github.cenafood.domain.model.Role;
import com.github.cenafood.domain.service.RoleService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController implements RoleControllerOpenApi {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper mapper;

    @CheckSecurity.UsersRolesPermission.Consult
    @GetMapping
    public CollectionModel<RoleResponseDTO> findAll() {
        return mapper.toCollectionModel(roleService.findAll());
    }

    @CheckSecurity.UsersRolesPermission.Consult
    @GetMapping("/{id}")
    public RoleResponseDTO findById(@PathVariable Long id) {
        return mapper.toModel(roleService.findById(id));
    }

    @CheckSecurity.UsersRolesPermission.Edit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO save(@RequestBody @Valid RoleRequestDTO role) {
        return mapper.toModel(roleService.save(mapper.toDomainEntity(role)));
    }

    @CheckSecurity.UsersRolesPermission.Edit
    @PutMapping("/{id}")
    public RoleResponseDTO update(@PathVariable Long id, @RequestBody @Valid RoleRequestDTO roleRequest) {
        Role role = roleService.findById(id);
        mapper.copyToDomainEntity(roleRequest, role);
        return mapper.toModel(roleService.save(role));
    }

    @CheckSecurity.UsersRolesPermission.Edit
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}

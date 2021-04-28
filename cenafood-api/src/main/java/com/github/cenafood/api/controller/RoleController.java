package com.github.cenafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.github.cenafood.api.mapper.RoleMapper;
import com.github.cenafood.api.model.request.RoleRequestDTO;
import com.github.cenafood.api.model.response.RoleResponseDTO;
import com.github.cenafood.api.openapi.controller.RoleControllerOpenApi;
import com.github.cenafood.domain.model.Role;
import com.github.cenafood.domain.service.RoleService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController implements RoleControllerOpenApi {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper mapper;

    @GetMapping
    public List<RoleResponseDTO> findAll() {
        return mapper.toCollectionDTO(roleService.findAll());
    }

    @GetMapping("/{id}")
    public RoleResponseDTO findById(@PathVariable Long id) {
        return mapper.toDTO(roleService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO save(@RequestBody @Valid RoleRequestDTO role) {
        return mapper.toDTO(roleService.save(mapper.toDomainEntity(role)));
    }

    @PutMapping("/{id}")
    public RoleResponseDTO update(@PathVariable Long id, @RequestBody @Valid RoleRequestDTO roleRequest) {
        Role role = roleService.findById(id);
        mapper.copyToDomainEntity(roleRequest, role);
        return mapper.toDTO(roleService.save(role));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}

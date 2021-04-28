package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.RoleMapper;
import com.github.cenafood.api.model.response.RoleResponseDTO;
import com.github.cenafood.api.openapi.controller.UserRoleControllerOpenApi;
import com.github.cenafood.domain.model.User;
import com.github.cenafood.domain.service.UserService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/users/{id}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRoleController implements UserRoleControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper mapper;

    @GetMapping
    public List<RoleResponseDTO> findRolesByUser(@PathVariable Long id) {
        User user = userService.findById(id);

        return mapper.toCollectionDTO(user.getRoles());
    }

    @PutMapping("/{idRole}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void addRoleToUser(@PathVariable Long id, @PathVariable Long idRole) {
        userService.addRoleToUser(id, idRole);
    }

    @DeleteMapping("/{idRole}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeRoleToUser(@PathVariable Long id, @PathVariable Long idRole) {
        userService.removeRoleToUser(id, idRole);
    }
}

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

import com.github.cenafood.api.v1.mapper.UserMapper;
import com.github.cenafood.api.v1.model.request.ChangePasswordRequestDTO;
import com.github.cenafood.api.v1.model.request.UserRequestDTO;
import com.github.cenafood.api.v1.model.request.UserWithPasswordRequestDTO;
import com.github.cenafood.api.v1.model.response.UserResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.UserControllerOpenApi;
import com.github.cenafood.domain.model.User;
import com.github.cenafood.domain.service.UserService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @GetMapping
    public CollectionModel<UserResponseDTO> findAll() {
        return mapper.toCollectionModel(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {
        return mapper.toModel(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@RequestBody @Valid UserWithPasswordRequestDTO user) {
        return mapper.toModel(userService.save(mapper.toDomainEntity(user)));
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequest) {
        User user = userService.findById(id);
        mapper.copyToDomainEntity(userRequest, user);
        return mapper.toModel(userService.save(user));
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long id, @RequestBody @Valid ChangePasswordRequestDTO userRequest) {
        userService.changePassword(id, userRequest.getCurrentPassword(), userRequest.getNewPassword(),
                userRequest.getConfirmPassword());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}

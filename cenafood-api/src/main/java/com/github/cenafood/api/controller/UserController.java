package com.github.cenafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.UserMapper;
import com.github.cenafood.api.model.request.ChangePasswordRequestDTO;
import com.github.cenafood.api.model.request.UserRequestDTO;
import com.github.cenafood.api.model.request.UserWithPasswordRequestDTO;
import com.github.cenafood.api.model.response.UserResponseDTO;
import com.github.cenafood.domain.model.User;
import com.github.cenafood.domain.service.UserService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper mapper;

	@GetMapping
	public List<UserResponseDTO> findAll() {
		return mapper.toCollectionDTO(userService.findAll());
	}

	@GetMapping("/{id}")
	public UserResponseDTO findById(@PathVariable Long id) {
		return mapper.toDTO(userService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDTO save(@RequestBody @Valid UserWithPasswordRequestDTO user) {
		return mapper.toDTO(userService.save(mapper.toDomainEntity(user)));
	}

	@PutMapping("/{id}")
	public UserResponseDTO update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequest) {
		User user = userService.findById(id);
		mapper.copyToDomainEntity(userRequest, user);
		return mapper.toDTO(userService.save(user));
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

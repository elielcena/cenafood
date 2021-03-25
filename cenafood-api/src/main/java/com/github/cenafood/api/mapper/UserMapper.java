package com.github.cenafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.UserRequestDTO;
import com.github.cenafood.api.model.request.UserWithPasswordRequestDTO;
import com.github.cenafood.api.model.response.UserResponseDTO;
import com.github.cenafood.domain.model.User;

/**
 * @author elielcena
 *
 */
@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserResponseDTO toDTO(User user) {
		return modelMapper.map(user, UserResponseDTO.class);
	}

	public List<UserResponseDTO> toCollectionDTO(Collection<User> user) {
		return user.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

	public User toDomainEntity(UserRequestDTO user) {
		return modelMapper.map(user, User.class);
	}

	public User toDomainEntityPassword(UserWithPasswordRequestDTO user) {
		return modelMapper.map(user, User.class);
	}

	public void copyToDomainEntity(UserRequestDTO userRequest, User user) {
		modelMapper.map(userRequest, user);
	}
}

package com.github.cenafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.CenaLinks;
import com.github.cenafood.api.controller.UserController;
import com.github.cenafood.api.model.request.UserRequestDTO;
import com.github.cenafood.api.model.request.UserWithPasswordRequestDTO;
import com.github.cenafood.api.model.response.UserResponseDTO;
import com.github.cenafood.domain.model.User;

/**
 * @author elielcena
 *
 */
@Component
public class UserMapper extends RepresentationModelAssemblerSupport<User, UserResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    public UserMapper() {
        super(UserController.class, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO toModel(User user) {
        UserResponseDTO userDTO = createModelWithId(user.getId(), user);
        modelMapper.map(user, userDTO);

        return userDTO.add(cenaLinks.linkToUsers())
                .add(cenaLinks.linkToRolesUser(userDTO.getId()));
    }

    @Override
    public CollectionModel<UserResponseDTO> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(UserController.class).withSelfRel());
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

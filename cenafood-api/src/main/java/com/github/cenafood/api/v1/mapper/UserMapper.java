package com.github.cenafood.api.v1.mapper;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.UserController;
import com.github.cenafood.api.v1.model.request.UserRequestDTO;
import com.github.cenafood.api.v1.model.request.UserWithPasswordRequestDTO;
import com.github.cenafood.api.v1.model.response.UserResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
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

    @Autowired
    private SecurityUtil securityUtil;

    public UserMapper() {
        super(UserController.class, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO toModel(User user) {
        UserResponseDTO userDTO = createModelWithId(user.getId(), user);
        modelMapper.map(user, userDTO);

        if (isTrue(securityUtil.consultUsersRolesPermissions())) {
            userDTO.add(cenaLinks.linkToUsers())
                    .add(cenaLinks.linkToRolesUser(userDTO.getId()));
        }

        return userDTO;
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

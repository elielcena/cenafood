package com.github.cenafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.v1.model.request.ChangePasswordRequestDTO;
import com.github.cenafood.api.v1.model.request.UserRequestDTO;
import com.github.cenafood.api.v1.model.request.UserWithPasswordRequestDTO;
import com.github.cenafood.api.v1.model.response.UserResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Users")
public interface UserControllerOpenApi {

    @ApiOperation("Search all payment methods")
    CollectionModel<UserResponseDTO> findAll();

    @ApiOperation("Search user by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid user ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "User not found", response = ErrorResponseDTO.class)
    })
    UserResponseDTO findById(@ApiParam(value = "User ID", example = "1", required = true) Long id);

    @ApiOperation("Register a new user")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered kitchen"),
    })
    UserResponseDTO save(@ApiParam(name = "body", value = "Representation of a new user", required = true) UserWithPasswordRequestDTO user);

    @ApiOperation("Update user by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated user"),
        @ApiResponse(code = 404, message = "User not found", response = ErrorResponseDTO.class)
    })
    UserResponseDTO update(@ApiParam(value = "User ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a user  with new data", required = true) UserRequestDTO userRequest);

    @ApiOperation("Update password")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Password changed successfully"),
        @ApiResponse(code = 404, message = "User not found", response = ErrorResponseDTO.class)
    })
    void changePassword(@ApiParam(value = "User ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a new password", required = true) ChangePasswordRequestDTO userRequest);

    @ApiOperation("Delete user")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Deleted user"),
        @ApiResponse(code = 404, message = "User not found", response = ErrorResponseDTO.class)
    })
    void delete(@ApiParam(value = "User ID", example = "1", required = true) Long id);
}

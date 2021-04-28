package com.github.cenafood.api.openapi.controller;

import java.util.List;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.response.RoleResponseDTO;

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
public interface UserRoleControllerOpenApi {

    @ApiOperation("Search for roles associated with user")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid user ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "User not found", response = ErrorResponseDTO.class)
    })
    List<RoleResponseDTO> findRolesByUser(@ApiParam(value = "User ID", example = "1", required = true) Long id);

    @ApiOperation("Association of role with user")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Role or user not found", response = ErrorResponseDTO.class)
    })
    void addRoleToUser(@ApiParam(value = "User ID", example = "1", required = true) Long id,
            @ApiParam(value = "Role ID", example = "1", required = true) Long idRole);

    @ApiOperation("Disassociation of role with user")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Role or user not found", response = ErrorResponseDTO.class)
    })
    void removeRoleToUser(@ApiParam(value = "User ID", example = "1", required = true) Long id,
            @ApiParam(value = "Role ID", example = "1", required = true) Long idRole);
}

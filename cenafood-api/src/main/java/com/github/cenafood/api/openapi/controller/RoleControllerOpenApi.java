package com.github.cenafood.api.openapi.controller;

import java.util.List;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.request.RoleRequestDTO;
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
@Api(tags = "Roles")
public interface RoleControllerOpenApi {

    @ApiOperation("Search all roles")
    List<RoleResponseDTO> findAll();

    @ApiOperation("Search role by id")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid role ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Role not found", response = ErrorResponseDTO.class)
    })
    RoleResponseDTO findById(@ApiParam(value = "Role ID", example = "1", required = true) Long id);

    @ApiOperation("Register a new role")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered role"),
    })
    RoleResponseDTO save(@ApiParam(name = "body", value = "Representation of a new role", required = true) RoleRequestDTO role);

    @ApiOperation("Update role by id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated role"),
        @ApiResponse(code = 404, message = "Role not found", response = ErrorResponseDTO.class)
    })
    RoleResponseDTO update(@ApiParam(value = "Role ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a new role with new data", required = true) RoleRequestDTO roleRequest);

    @ApiOperation("Delete role by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Role deleted"),
        @ApiResponse(code = 404, message = "Role not found", response = ErrorResponseDTO.class)
    })
    void delete(@ApiParam(value = "Role ID", example = "1", required = true) Long id);
}

package com.github.cenafood.api.openapi.controller;

import java.util.List;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.response.PermissionResponseDTO;

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
public interface RolePermissionControllerOpenApi {

    @ApiOperation("Search for permissions associated with role")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid role ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Role not found", response = ErrorResponseDTO.class)
    })
    List<PermissionResponseDTO> listar(@ApiParam(value = "Role ID", example = "1", required = true) Long id);

    @ApiOperation("Association of role with permission")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Role or permission not found", response = ErrorResponseDTO.class)
    })
    void addPermission(@ApiParam(value = "Role ID", example = "1", required = true) Long id,
            @ApiParam(value = "Permission ID", example = "1", required = true) Long idPermission);

    @ApiOperation("Disassociation of role with permission")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Role or permission not found", response = ErrorResponseDTO.class)
    })
    void removePermission(@ApiParam(value = "Role ID", example = "1", required = true) Long id,
            @ApiParam(value = "Permission ID", example = "1", required = true) Long idPermission);
}

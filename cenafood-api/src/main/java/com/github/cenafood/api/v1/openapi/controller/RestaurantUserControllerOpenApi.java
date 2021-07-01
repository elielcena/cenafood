package com.github.cenafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.v1.model.response.UserResponseDTO;
import com.github.cenafood.core.openapi.FieldsResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Restaurants")
public interface RestaurantUserControllerOpenApi {

    @FieldsResponse
    @ApiOperation("Search for responsible users associated with restaurants")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid restaurant ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    CollectionModel<UserResponseDTO> find(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @ApiOperation("Association of restaurant with user")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant or user not found", response = ErrorResponseDTO.class)
    })
    ResponseEntity<Void> addUserToRestaurant(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "User ID", example = "1", required = true) Long idUser);

    @ApiOperation("Disassociation of with user")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant or puser not found", response = ErrorResponseDTO.class)
    })
    ResponseEntity<Void> removeUserToRestaurant(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "User ID", example = "1", required = true) Long idUser);
}

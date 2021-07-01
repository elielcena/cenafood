package com.github.cenafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.request.KitchenRequestDTO;
import com.github.cenafood.api.model.response.KitchenResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Kitchens")
public interface KitchenControllerOpenApi {

    @ApiOperation("Search all kitchens")
    CollectionModel<KitchenResponseDTO> findAll();

    @ApiOperation("Search kitchen by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid kitchen ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Kitchen not found", response = ErrorResponseDTO.class)
    })
    KitchenResponseDTO findById(@ApiParam(value = "Kitchen ID", example = "1", required = true) Long id);

    @ApiOperation("Register a new kitchen")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered kitchen"),
    })
    KitchenResponseDTO save(@ApiParam(name = "body", value = "Representation of a new kitchen", required = true) KitchenRequestDTO kitchen);

    @ApiOperation("Update kitchen by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated kitchen"),
        @ApiResponse(code = 404, message = "Kitchen not found", response = ErrorResponseDTO.class)
    })
    KitchenResponseDTO update(@ApiParam(value = "Kitchen ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a kitchen with new data", required = true) KitchenRequestDTO kitchenRequest);

    @ApiOperation("Delete kitchen by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Kitchen deleted"),
        @ApiResponse(code = 404, message = "Kitchen not found", response = ErrorResponseDTO.class)
    })
    void delete(@ApiParam(value = "Kitchen ID", example = "1", required = true) Long id);

}

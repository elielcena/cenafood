package com.github.cenafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.request.RestaurantRequestDTO;
import com.github.cenafood.api.model.response.RestaurantResponseDTO;
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
public interface RestaurantControllerOpenApi {

    @FieldsResponse
    @ApiOperation("Search all restaurants")
    ResponseEntity<List<RestaurantResponseDTO>> findAll();

    @FieldsResponse
    @ApiOperation("Search restaurant by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid restaurant ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    ResponseEntity<RestaurantResponseDTO> findById(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @ApiOperation("Register a new restaurant")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered restaurant"),
    })
    RestaurantResponseDTO save(@ApiParam(name = "body", value = "Representation of a new restaurant", required = true) RestaurantRequestDTO restaurant);

    @ApiOperation("Update restaurant by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated restaurant"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    RestaurantResponseDTO update(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a restaurant with new data", required = true) RestaurantRequestDTO restaurantRequest);

    @ApiOperation("Activate restaurant by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant activated"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    void activate(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @ApiOperation("Disable restaurant by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant disabled"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    void inactivate(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @ApiOperation("Open restaurant")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant opened"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    void opening(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @ApiOperation("Close restaurant")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurant closed"),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    void closure(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

}

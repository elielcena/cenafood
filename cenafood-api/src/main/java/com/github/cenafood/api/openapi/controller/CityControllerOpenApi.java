package com.github.cenafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.domain.model.City;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("Search city by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid city ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "City not found", response = ErrorResponseDTO.class)
    })
    City findById(@ApiParam(value = "City ID", example = "1", required = true) Long id);

    @ApiOperation("Search all cities with filters")
    CollectionModel<City> findAllWithFilter(City filtro);

}

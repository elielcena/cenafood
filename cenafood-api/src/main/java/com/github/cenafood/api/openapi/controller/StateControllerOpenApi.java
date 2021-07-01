package com.github.cenafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.domain.model.State;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("Search state by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid state UF", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "State not found", response = ErrorResponseDTO.class)
    })
    State findByUf(@ApiParam(value = "State UF", example = "1", required = true) String uf);

    @ApiOperation("Search all states with filters")
    CollectionModel<State> findAllWithFilter(State filtro);
}

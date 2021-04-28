package com.github.cenafood.api.openapi.controller;

import java.util.List;

import com.github.cenafood.domain.model.State;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author elielcena
 *
 */
@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("Search all states with filters")
    List<State> findAllWithFilter(State filtro);
}

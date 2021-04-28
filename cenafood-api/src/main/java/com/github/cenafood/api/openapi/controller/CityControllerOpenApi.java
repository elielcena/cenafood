package com.github.cenafood.api.openapi.controller;

import java.util.List;

import com.github.cenafood.domain.model.City;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author elielcena
 *
 */
@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("Search all cities with filters")
    List<City> findAllWithFilter(City filtro);

}

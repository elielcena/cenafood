package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.openapi.controller.CityControllerOpenApi;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.service.CityService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> findAllWithFilter(City filtro) {
        return cityService.findAllWithFilters(filtro);
    }
}

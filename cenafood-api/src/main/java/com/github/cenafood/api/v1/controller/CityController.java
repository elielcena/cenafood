package com.github.cenafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.openapi.controller.CityControllerOpenApi;
import com.github.cenafood.core.security.annotation.CheckSecurity;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.service.CityService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(path = "/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @Autowired
    private CenaLinks cenaLinks;

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        City city = cityService.findById(id);

        return addLinksCity(city);
    }

    @CheckSecurity.NoPreAuthorizeRead
    @GetMapping
    public CollectionModel<City> findAllWithFilter(City filtro) {
        CollectionModel<City> citiesModel = CollectionModel.of(cityService.findAllWithFilters(filtro));

        citiesModel.add(cenaLinks.linkToCities().withSelfRel());

        citiesModel.getContent().forEach(this::addLinksCity);

        return citiesModel;
    }

    private City addLinksCity(City city) {
        city.add(cenaLinks.linkToCity(city.getId()));
        city.getState().add(cenaLinks.linkToState(city.getState().getUf()));
        city.add(cenaLinks.linkToCities());

        return city;
    }

}

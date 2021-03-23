package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.service.CityService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	public List<City> findAllWithFilter(City filtro) {
		return cityService.findAllWithFilters(filtro);
	}
}

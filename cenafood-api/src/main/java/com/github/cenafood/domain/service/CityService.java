package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.repository.CityRepository;

/**
 * @author elielcena
 *
 */
@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public List<City> findAllWithFilters(City filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		return cityRepository.findAll(Example.of(filtro, matcher));
	}

}

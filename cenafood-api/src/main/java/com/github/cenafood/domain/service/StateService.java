package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.model.State;
import com.github.cenafood.domain.repository.StateRepository;

/**
 * @author elielcena
 *
 */
@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<State> findAllWithFilter(State filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		return stateRepository.findAll(Example.of(filtro, matcher));
	}

}

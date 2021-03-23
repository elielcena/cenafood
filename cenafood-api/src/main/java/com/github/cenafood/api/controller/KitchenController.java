package com.github.cenafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.service.KitchenService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	public List<Kitchen> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{id}")
	public Kitchen findById(@PathVariable Long id) {
		return kitchenService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody @Valid Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	@PutMapping("/{id}")
	public Kitchen update(@PathVariable Long id, @RequestBody @Valid Kitchen kitchen) {
		return kitchenService.update(id, kitchen);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		kitchenService.delete(id);
	}

}

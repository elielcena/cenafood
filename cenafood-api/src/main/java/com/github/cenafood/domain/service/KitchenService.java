package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.EntityInUseException;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.repository.KitchenRepository;

/**
 * @author elielcena
 *
 */
@Service
public class KitchenService {

	private static final String MSG_KITCHEN_NOT_FOUND = "There is no kitchen register with code %d";

	@Autowired
	private KitchenRepository kitchenRepository;

	public List<Kitchen> findAll() {
		return kitchenRepository.findAll();
	}

	public Kitchen findById(Long id) {
		return kitchenRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id)));
	}

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public Kitchen update(Long id, Kitchen kitchen) {
		Kitchen currentKitchen = findById(id);

		BeanUtils.copyProperties(kitchen, currentKitchen, "id");

		return save(currentKitchen);
	}

	public void delete(Long id) {
		try {
			kitchenRepository.delete(findById(id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException();
		}
	}

}

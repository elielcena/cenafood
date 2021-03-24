package com.github.cenafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.service.KitchenService;

@SpringBootTest
class KitchenServiceTests {

	private static final String KITCHEN_NAME = "Eliel Delivery";

	@Autowired
	private KitchenService kitchenService;

	@Test
	void shouldAssignId_WhenEnteringKitchenDataWithCorrectData() {
		// scenery
		Kitchen kitchen = Kitchen.builder().name(KITCHEN_NAME).build();

		// action
		kitchen = kitchenService.save(kitchen);

		// validation
		assertThat(kitchen).isNotNull();
		assertThat(kitchen.getId()).isNotNull();
	}

	@Test
	void shouldThrowException_WhenRegisteringUnnamedKitchen() {
		Kitchen kitchen = Kitchen.builder().name(null).build();

		assertThrows(ConstraintViolationException.class, () -> kitchenService.save(kitchen));
	}

	@Test
	void shouldThrowException_WhenNotFindKitchenById() {
		assertThrows(ResourceNotFoundException.class, () -> kitchenService.findById(1000L));
	}

	@Test
	void shouldAssignId_WhenUpdatingKitchenDataWithCorrectData() {
		Kitchen kitchen = Kitchen.builder().name(KITCHEN_NAME).build();

		kitchen = kitchenService.update(1L, kitchen);

		assertThat(kitchen).isNotNull();
		assertThat(kitchen.getId()).isNotNull();
		assertThat(kitchen.getId()).isEqualTo(1L);
	}

	@Test
	void shouldThrowException_WhenUpdateNonExistentKitchen() {
		Kitchen kitchen = Kitchen.builder().name(KITCHEN_NAME).build();

		assertThrows(ResourceNotFoundException.class, () -> kitchenService.update(1000L, kitchen));
	}

	@Test
	void shouldThrowException_WhenExcludeKitchenInUse() {
		assertThrows(DataIntegrityViolationException.class, () -> kitchenService.delete(2L));
	}

	@Test
	void shouldExclude_WhenThereIsKitchenAndNotInUse() {
		kitchenService.delete(3L);

		assertThrows(ResourceNotFoundException.class, () -> kitchenService.findById(4L));
	}

	@Test
	void shouldThrowException_WhenExcludeNonExistentKitchen() {
		assertThrows(ResourceNotFoundException.class, () -> kitchenService.delete(1000L));
	}
}

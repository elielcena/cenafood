package com.github.cenafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.github.cenafood.domain.model.Restaurant;

/**
 * @author elielcena
 *
 */
public class RestaurantSpecs {

	public static Specification<Restaurant> withFreeShipping() {
		return (root, query, builder) -> builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
	}

	public static Specification<Restaurant> withNameSimilar(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}

}

package com.github.cenafood.infrastructure.repository.spec;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.github.cenafood.domain.filter.OrderFilter;
import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
public class OrderSpecs {

	public static Specification<Order> withFilter(OrderFilter filter) {
		return (root, query, builder) -> {
			if (Order.class.equals(query.getResultType())) {
				root.fetch("restaurant").fetch("kitchen");
				root.fetch("customer");
			}

			var predicates = new ArrayList<Predicate>();

			if (Optional.ofNullable(filter.getIdCustomer()).isPresent()) {
				predicates.add(builder.equal(root.get("customer"), filter.getIdCustomer()));
			}
			if (Optional.ofNullable(filter.getIdRestaurant()).isPresent()) {
				predicates.add(builder.equal(root.get("restaurant"), filter.getIdRestaurant()));
			}
			if (Optional.ofNullable(filter.getCreationStartDate()).isPresent()) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreationStartDate()));
			}
			if (Optional.ofNullable(filter.getCreationEndDate()).isPresent()) {
				predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filter.getCreationEndDate()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

	public static Specification<Order> withNameSimilar(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}

}

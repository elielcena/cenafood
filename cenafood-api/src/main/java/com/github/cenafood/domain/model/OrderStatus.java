package com.github.cenafood.domain.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author elielcena
 *
 */
public enum OrderStatus {

	CREATED("Created"),
	CONFIRMED("Confirmed", CREATED),
	DELIVERED("Delivered", CONFIRMED),
	CANCELED("Canceled", CREATED);

	private String description;
	private List<OrderStatus> previousStatus;

	OrderStatus(String description, OrderStatus... previousStatus) {
		this.description = description;
		this.previousStatus = Arrays.asList(previousStatus);
	}

	public String getDescription() {
		return this.description;
	}

	public Boolean cannotChangeTo(OrderStatus newStatus) {
		return !newStatus.previousStatus.contains(this);
	}

}

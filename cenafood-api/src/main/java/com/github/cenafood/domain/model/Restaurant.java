package com.github.cenafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "DELIVERYFEE", nullable = false)
	private BigDecimal deliveryFee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDKITCHEN", nullable = false)
	private Kitchen kitchen;

	@Embedded
	private Address address;

	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean active;

	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean open;

	@CreationTimestamp
	@Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "UPDATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime updatedAt;

	@ManyToMany
	@JoinTable(name = "RESTAURANTPAYMENT", joinColumns = @JoinColumn(name = "IDRESTAURANT"), inverseJoinColumns = @JoinColumn(name = "IDPAYMENTMETHOD"))
	private Set<PaymentMethod> paymentMethods;

	@OneToMany(mappedBy = "restaurant")
	private Set<Product> products;

	public Restaurant activate() {
		setActive(Boolean.TRUE);
		return this;
	}

	public Restaurant inactivate() {
		setActive(Boolean.FALSE);
		return this;
	}

	public Restaurant opening() {
		setOpen(Boolean.TRUE);
		return this;
	}

	public Restaurant closure() {
		setOpen(Boolean.FALSE);
		return this;
	}

	public Restaurant addOrRemovePaymentMethod(Boolean isAdd, PaymentMethod paymentMethod) {
		if (Boolean.TRUE.equals(isAdd))
			getPaymentMethods().add(paymentMethod);
		else
			getPaymentMethods().remove(paymentMethod);

		return this;
	}

	public Restaurant addOrRemoveProduct(Boolean isAdd, Product product) {
		if (Boolean.TRUE.equals(isAdd))
			getProducts().add(product);
		else
			getProducts().remove(product);

		return this;
	}

}

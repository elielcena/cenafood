package com.github.cenafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	@JoinColumn(name = "IDKITCHEN", nullable = false)
	private Kitchen kitchen;

	@Embedded
	private Adress adress;

	@CreationTimestamp
	@Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "UPDATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	@JoinTable(name = "RESTAURANTPAYMENT", joinColumns = @JoinColumn(name = "IDRESTAURANT"), inverseJoinColumns = @JoinColumn(name = "IDPAYMENTMETHOD"))
	private List<PaymentMethod> paymentMethods;

	@OneToMany(mappedBy = "restaurant")
	private List<Product> products;

}

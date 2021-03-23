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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.cenafood.core.validation.Groups;

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

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotNull
	@PositiveOrZero
	@Column(name = "DELIVERYFEE", nullable = false)
	private BigDecimal deliveryFee;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.KithcenId.class)
	@ManyToOne
	@JoinColumn(name = "IDKITCHEN", nullable = false)
	private Kitchen kitchen;

	@JsonIgnore
	@Embedded
	private Adress adress;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "UPDATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "RESTAURANTPAYMENT", joinColumns = @JoinColumn(name = "IDRESTAURANT"), inverseJoinColumns = @JoinColumn(name = "IDPAYMENTMETHOD"))
	private List<PaymentMethod> paymentMethods;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products;

}

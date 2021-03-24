package com.github.cenafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

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
@Table(name = "ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "pg-uuid")
	@Column(name = "CODE", length = 10, updatable = false, unique = true, nullable = false)
	private UUID code;

	@Column(name = "SUBTOTAL")
	private BigDecimal subtotal;

	@Column(name = "DELIVERYFEE")
	private BigDecimal deliveryfee;

	@Column(name = "TOTALPRICE")
	private BigDecimal totalPrice;

	@Embedded
	private Adress adress;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@CreationTimestamp
	@Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime createdAt;

	@Column(name = "CONFIRMEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime confirmedAt;

	@Column(name = "CANCELEDAT", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime canceledAt;

	@Column(name = "DELIVERYAT", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime deliveredAt;

	@ManyToOne
	@JoinColumn(name = "IDPAYMENTMETHOD", nullable = false)
	private PaymentMethod paymentMethod;

	@ManyToOne
	@JoinColumn(name = "IDRESTAURANT", nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "IDUSER", nullable = false)
	private User user;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;

}

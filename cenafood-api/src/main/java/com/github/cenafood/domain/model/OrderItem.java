package com.github.cenafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "ORDERITEM")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "UNITPRICE")
	private BigDecimal unitPrice;

	@Column(name = "TOTALPRICE")
	private BigDecimal totalPrice;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;

	@Column(name = "NOTE")
	private String note;

	@ManyToOne
	@JoinColumn(name = "IDORDER", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "IDPRODUCT", nullable = false)
	private Product product;

}

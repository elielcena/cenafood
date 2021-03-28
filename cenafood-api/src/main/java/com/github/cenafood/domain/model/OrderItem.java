package com.github.cenafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDORDER", nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPRODUCT", nullable = false)
	private Product product;

	public void calculateTotalPrice() {
		BigDecimal unitPriceCalculate = this.unitPrice;
		Integer quantityCalculate = this.quantity;

		if (unitPriceCalculate == null) {
			unitPriceCalculate = BigDecimal.ZERO;
		}

		if (quantityCalculate == null) {
			quantityCalculate = 0;
		}

		this.totalPrice = unitPriceCalculate.multiply(new BigDecimal(quantityCalculate));
	}

}

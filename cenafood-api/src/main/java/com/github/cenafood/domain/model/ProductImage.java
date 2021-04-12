package com.github.cenafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.cenafood.api.model.request.ProductImageRequestDTO;

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
@Table(name = "PRODUCTIMAGE")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPRODUCT", nullable = false)
	private Product product;

	@Column(name = "FILENAME", nullable = false)
	private String fileName;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "CONTENTTYPE", nullable = false)
	private String contentType;

	@Column(name = "SIZE", nullable = false)
	private Long size;

	public static ProductImage fromProductImageRequestDTO(ProductImageRequestDTO productImageRequest, Product product) {
		ProductImage productImage = new ProductImage();
		productImage.setProduct(product);
		productImage.setDescription(productImageRequest.getDescription());
		productImage.setContentType(productImageRequest.getFile().getContentType());
		productImage.setSize(productImageRequest.getFile().getSize());
		productImage.setFileName(productImageRequest.getFile().getOriginalFilename());
		return productImage;
	}

}

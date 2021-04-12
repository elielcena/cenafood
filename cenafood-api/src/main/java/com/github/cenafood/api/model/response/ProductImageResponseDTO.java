package com.github.cenafood.api.model.response;

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
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponseDTO {

	private Long id;

	private String fileName;

	private String description;

	private String contentType;

	private Long size;

}

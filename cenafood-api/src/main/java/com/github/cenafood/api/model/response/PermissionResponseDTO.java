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
public class PermissionResponseDTO {

	private Long id;

	private String name;
	
	private String description;

}

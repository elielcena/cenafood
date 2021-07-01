package com.github.cenafood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Relation(collectionRelation = "permissions")
@ApiModel("PermissionResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDTO extends RepresentationModel<PermissionResponseDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "EDIT")
    private String name;

    @ApiModelProperty(example = "EDIT RESTAURANT")
    private String description;

}

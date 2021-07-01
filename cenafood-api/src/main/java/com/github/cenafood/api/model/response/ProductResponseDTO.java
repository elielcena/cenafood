package com.github.cenafood.api.model.response;

import java.math.BigDecimal;

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
@Relation(collectionRelation = "products")
@ApiModel("ProductResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO extends RepresentationModel<ProductResponseDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "COCA-COLA 2L")
    private String name;

    @ApiModelProperty(example = "COCA-COLA 2L")
    private String description;

    @ApiModelProperty(example = "10.00")
    private BigDecimal price;

    @ApiModelProperty(example = "true")
    private Boolean active;

}

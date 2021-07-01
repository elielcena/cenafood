package com.github.cenafood.api.v1.model.response;

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
@Relation(collectionRelation = "restaurants")
@ApiModel("RestaurantResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDTO extends RepresentationModel<RestaurantResponseDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "E'Cena Delivery")
    private String name;

    @ApiModelProperty(example = "10.5")
    private BigDecimal deliveryFee;

    private KitchenResponseDTO kitchen;

    @ApiModelProperty(example = "true")
    private Boolean active;

    @ApiModelProperty(example = "true")
    private Boolean open;

    private AddressResponseDTO address;

}

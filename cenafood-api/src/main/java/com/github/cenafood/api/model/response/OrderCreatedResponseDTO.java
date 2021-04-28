package com.github.cenafood.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@ApiModel("OrderCreatedResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedResponseDTO {

    @ApiModelProperty(example = "fcffa16d-d918-4deb-a3e6-cc46e096514")
    private String code;

}

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
@ApiModel("ProductImageResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_coca-cola.jpg")
    private String fileName;

    @ApiModelProperty(example = "COCA-COLA 2L")
    private String description;

    @ApiModelProperty(example = "image/jpeg")
    private String contentType;

    @ApiModelProperty(example = "65001")
    private Long size;

}

package com.github.cenafood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("PageResponse")
@Data
public class PageResponseOpenApi {

    @ApiModelProperty(value = "Number of elements per page", example = "10")
    private Long size;

    @ApiModelProperty(value = "Total of elements", example = "50")
    private Long totalElements;

    @ApiModelProperty(value = "Number of pages", example = "5")
    private Long totalPages;

    @ApiModelProperty(value = "Number of page (start at 0)", example = "0")
    private Long number;

}

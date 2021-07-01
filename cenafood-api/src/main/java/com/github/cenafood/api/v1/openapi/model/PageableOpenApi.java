package com.github.cenafood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("Pageable")
@Data
public class PageableOpenApi {

    @ApiModelProperty(value = "Number of page (start at 0)", example = "1")
    private int page;

    @ApiModelProperty(value = "Number of elements per page", example = "10")
    private int size;

    @ApiModelProperty(value = "Property name for sorting", example = "totalPrice,asc")
    private List<String> sort;

}

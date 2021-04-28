package com.github.cenafood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@Data
public class PageOpenApi<T> {

    private List<T> content;

    @ApiModelProperty(value = "Number of elements per page", example = "10")
    private Long size;

    @ApiModelProperty(value = "Total of elements", example = "50")
    private Long totalElements;

    @ApiModelProperty(value = "Number of pages", example = "5")
    private Long totalPages;

    @ApiModelProperty(value = "Number of page (start at 0)", example = "0")
    private Long number;

}

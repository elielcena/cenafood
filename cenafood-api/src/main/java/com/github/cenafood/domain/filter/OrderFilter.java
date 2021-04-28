package com.github.cenafood.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elielcena
 *
 */

@Getter
@Setter
public class OrderFilter {

    @ApiModelProperty(example = "1", required = false)
    private Long idCustomer;

    @ApiModelProperty(example = "1", required = false)
    private Long idRestaurant;

    @ApiModelProperty(example = "2021-04-01", required = false)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate startDate;

    @ApiModelProperty(example = "2021-04-30", required = false)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate endDate;

}

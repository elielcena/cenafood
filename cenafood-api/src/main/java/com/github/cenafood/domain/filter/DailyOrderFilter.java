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
public class DailyOrderFilter {

    @ApiModelProperty(example = "1")
    private Long idRestaurant;

    @ApiModelProperty(example = "2021-04-01")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate startDate;

    @ApiModelProperty(example = "2021-04-30")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate endDate;

}

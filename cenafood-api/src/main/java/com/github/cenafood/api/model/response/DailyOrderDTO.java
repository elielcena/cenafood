package com.github.cenafood.api.model.response;

import java.math.BigDecimal;
import java.util.Date;

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
@ApiModel("DailyOrderResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyOrderDTO {

    @ApiModelProperty(example = "2021-04-27")
    private Date date;

    @ApiModelProperty(example = "15")
    private Long totalOrder;

    @ApiModelProperty(example = "2600.00")
    private BigDecimal totalBilled;

}

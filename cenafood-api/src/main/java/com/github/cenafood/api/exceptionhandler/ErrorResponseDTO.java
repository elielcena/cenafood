package com.github.cenafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@ApiModel("ErrorResponse")
@JsonInclude(Include.NON_NULL)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {

    @ApiModelProperty(example = "2021-04-21T20:31:58.1029978-03:00", position = 1)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "400", position = 5)
    private Integer status;

    @ApiModelProperty(example = "Bad Request", position = 10)
    private String error;

    @ApiModelProperty(example = "One or more fields are invalid", position = 15)
    private String message;

    @ApiModelProperty(value = "List of error causes, with name and message (Optinal)", position = 20)
    private List<Cause> causes;

    @ApiModelProperty(example = "/users", position = 25)
    private String path;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Cause {

        @ApiModelProperty(example = "name")
        private String name;

        @ApiModelProperty(example = "Name is required")
        private String message;
    }

}

package com.github.cenafood.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@Data
@ApiModel("Links")
public class LinksResponseOpenApi {

    private LinkModel rel;

    @Data
    @ApiModel("Link")
    private class LinkModel {

        private String href;

        private String templated;

    }
}

package com.github.cenafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.v1.model.response.RoleResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("RolesResponse")
@Data
public class RoleResponseOpenApi {

    private RoleEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("RolesEmbeddedResponse")
    @Data
    private class RoleEmbeddedResponseOpenApi {

        private List<RoleResponseDTO> roles;

    }

}

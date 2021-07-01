package com.github.cenafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.v1.model.response.PermissionResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("PermissionsResponse")
@Data
public class PermissionResponseOpenApi {

    private PermissionEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("PermissionsEmbeddedResponse")
    @Data
    private class PermissionEmbeddedResponseOpenApi {

        private List<PermissionResponseDTO> permissions;

    }

}

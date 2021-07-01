package com.github.cenafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.model.response.UserResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("UsersResponse")
@Data
public class UserResponseOpenApi {

    private UserEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("UsersEmbeddedResponse")
    @Data
    private class UserEmbeddedResponseOpenApi {

        private List<UserResponseDTO> users;

    }

}

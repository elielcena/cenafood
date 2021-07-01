package com.github.cenafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.domain.model.State;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("StatesResponse")
@Data
public class StateResponseOpenApi {

    private StateEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("StatesEmbeddedResponse")
    @Data
    private class StateEmbeddedResponseOpenApi {

        private List<State> states;

    }

}

package com.github.cenafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.openapi.controller.StateControllerOpenApi;
import com.github.cenafood.core.security.annotation.CheckSecurity;
import com.github.cenafood.domain.model.State;
import com.github.cenafood.domain.service.StateService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateService stateService;

    @Autowired
    private CenaLinks cenaLinks;

    @CheckSecurity.NoPreAuthorizeRead
    @GetMapping("/{uf}")
    public State findByUf(@PathVariable String uf) {
        State state = stateService.findByUf(uf);

        return addLinksState(state);
    }

    @CheckSecurity.NoPreAuthorizeRead
    @GetMapping
    public CollectionModel<State> findAllWithFilter(State filtro) {
        CollectionModel<State> stateModel = CollectionModel.of(stateService.findAllWithFilter(filtro));

        stateModel.add(cenaLinks.linkToStates().withSelfRel());
        stateModel.getContent().forEach(this::addLinksState);

        return stateModel;
    }

    private State addLinksState(State state) {
        return state.add(cenaLinks.linkToState(state.getUf()))
                .add(cenaLinks.linkToStates());
    }
}

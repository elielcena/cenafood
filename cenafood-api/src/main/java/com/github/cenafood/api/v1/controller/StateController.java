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
import com.github.cenafood.domain.model.State;
import com.github.cenafood.domain.service.StateService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateService stateService;

    @Autowired
    private static CenaLinks cenaLinks;

    @GetMapping("/{uf}")
    public State findByUf(@PathVariable String uf) {
        State state = stateService.findByUf(uf);

        return addLinksState(state);
    }

    @GetMapping
    public CollectionModel<State> findAllWithFilter(State filtro) {
        CollectionModel<State> stateModel = CollectionModel.of(stateService.findAllWithFilter(filtro));

        stateModel.add(cenaLinks.linkToStates().withSelfRel());
        stateModel.getContent().forEach(StateController::addLinksState);

        return stateModel;
    }

    private static State addLinksState(State state) {
        return state.add(cenaLinks.linkToState(state.getUf()))
                .add(cenaLinks.linkToStates());
    }
}

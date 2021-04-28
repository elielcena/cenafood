package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.openapi.controller.StateControllerOpenApi;
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

    @GetMapping
    public List<State> findAllWithFilter(State filtro) {
        return stateService.findAllWithFilter(filtro);
    }
}

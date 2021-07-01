package com.github.cenafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.KitchenMapper;
import com.github.cenafood.api.model.request.KitchenRequestDTO;
import com.github.cenafood.api.model.response.KitchenResponseDTO;
import com.github.cenafood.api.openapi.controller.KitchenControllerOpenApi;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.service.KitchenService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController implements KitchenControllerOpenApi {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenMapper mapper;

    @GetMapping
    public CollectionModel<KitchenResponseDTO> findAll() {
        return mapper.toCollectionModel(kitchenService.findAll());
    }

    @GetMapping("/{id}")
    public KitchenResponseDTO findById(@PathVariable Long id) {
        return mapper.toModel(kitchenService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenResponseDTO save(@RequestBody @Valid KitchenRequestDTO kitchen) {
        return mapper.toModel(kitchenService.save(mapper.toDomainEntity(kitchen)));
    }

    @PutMapping("/{id}")
    public KitchenResponseDTO update(@PathVariable Long id, @RequestBody @Valid KitchenRequestDTO kitchenRequest) {
        Kitchen kitchen = kitchenService.findById(id);
        mapper.copyToDomainEntity(kitchenRequest, kitchen);
        return mapper.toModel(kitchenService.save(kitchen));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }

}

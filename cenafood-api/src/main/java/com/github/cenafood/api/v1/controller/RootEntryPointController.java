package com.github.cenafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;

/**
 * @author elielcena
 *
 */
@Api(tags = "Root")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private CenaLinks cenaLinks;

    @ApiOperation("Root Entry Point")
    @GetMapping
    public RootEntryPointResponse root() {
        return RootEntryPointResponse.builder().build()
                .add(cenaLinks.linkToOrders().withRel("orders"))
                .add(cenaLinks.linkToRestaurants().withRel("restaurants"))
                .add(cenaLinks.linkToKitchens().withRel("kitchens"))
                .add(cenaLinks.linkToUsers().withRel("users"))
                .add(cenaLinks.linkToRoles().withRel("roles"))
                .add(cenaLinks.linkToPermission(null).withRel("permissions"))
                .add(cenaLinks.linkToPaymentMethods().withRel("paymentMethods"))
                .add(cenaLinks.linkToCities().withRel("cities"))
                .add(cenaLinks.linkToStates().withRel("states"))
                .add(cenaLinks.linkToStatistics());
    }

    @ApiModel("RootEntryPointResponse")
    @Builder
    private static class RootEntryPointResponse extends RepresentationModel<RootEntryPointResponse> {

    }
}

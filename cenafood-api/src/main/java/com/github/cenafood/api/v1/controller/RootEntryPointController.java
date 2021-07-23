package com.github.cenafood.api.v1.controller;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.core.security.SecurityUtil;

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
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private CenaLinks cenaLinks;

    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation("Root Entry Point")
    @GetMapping
    public RootEntryPointResponse root() {
        var rootEntryPointModel = new RootEntryPointResponse();

        if (BooleanUtils.isTrue(securityUtil.noPreAuthorizeRead())) {
            rootEntryPointModel.add(cenaLinks.linkToOrders().withRel("orders"));
            rootEntryPointModel.add(cenaLinks.linkToRestaurants().withRel("restaurants"));
            rootEntryPointModel.add(cenaLinks.linkToKitchens().withRel("kitchens"));
            rootEntryPointModel.add(cenaLinks.linkToPaymentMethods().withRel("paymentMethods"));
            rootEntryPointModel.add(cenaLinks.linkToCities().withRel("cities"));
            rootEntryPointModel.add(cenaLinks.linkToStates().withRel("states"));
        }

        if (BooleanUtils.isTrue(securityUtil.consultUsersRolesPermissions())) {
            rootEntryPointModel.add(cenaLinks.linkToUsers().withRel("users"));
            rootEntryPointModel.add(cenaLinks.linkToRoles().withRel("roles"));
            rootEntryPointModel.add(cenaLinks.linkToPermission(null).withRel("permissions"));
        }

        if (BooleanUtils.isTrue(securityUtil.consultStatistic())) {
            rootEntryPointModel.add(cenaLinks.linkToStatistics());
        }

        return rootEntryPointModel;
    }

    @ApiModel("RootEntryPointResponse")
    @Builder
    private static class RootEntryPointResponse extends RepresentationModel<RootEntryPointResponse> {

    }
}

package com.github.cenafood.api.openapi.controller;

import java.util.List;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.core.openapi.FieldsResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Restaurants")
public interface RestaurantPaymentControllerOpenApi {

    @FieldsResponse
    @ApiOperation("Search payment methods associated with restaurants")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid restaurant ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    List<PaymentMethodResponseDTO> find(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @ApiOperation("Association of restaurant with payment method")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Association successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant or payment method not found", response = ErrorResponseDTO.class)
    })
    void addPaymentMethodToRestaurant(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Payment method ID", example = "1", required = true) Long idPaymentMethod);

    @ApiOperation("Disassociation of restaurant with payment method")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Disassociation successfully", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant or payment method not found", response = ErrorResponseDTO.class)
    })
    void removePaymentMethodToRestaurant(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Payment method ID", example = "1", required = true) Long idPaymentMethod);

}

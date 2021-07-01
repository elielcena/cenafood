package com.github.cenafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.v1.model.request.PaymentMethodRequestDTO;
import com.github.cenafood.api.v1.model.response.PaymentMethodResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Payment Methods")
public interface PaymentMethodControllerOpenApi {

    @ApiOperation("Search all payment methods")
    CollectionModel<PaymentMethodResponseDTO> findAll();

    @ApiOperation("Search Payment method by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid payment method ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Payment method not found", response = ErrorResponseDTO.class)
    })
    PaymentMethodResponseDTO findById(@ApiParam(value = "Payment method ID", example = "1", required = true) Long id);

    @ApiOperation("Register a new payment method")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered payment method"),
    })
    PaymentMethodResponseDTO save(
            @ApiParam(name = "body", value = "Representation of a new payment method", required = true) PaymentMethodRequestDTO paymentMethod);

    @ApiOperation("Update payment method by ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated payment method"),
        @ApiResponse(code = 404, message = "Payment method not found", response = ErrorResponseDTO.class)
    })
    PaymentMethodResponseDTO update(@ApiParam(value = "Payment method ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a payment method with new data",
                    required = true) PaymentMethodRequestDTO paymentMethodRequest);

    @ApiOperation("Delete payment method by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Payment method deleted"),
        @ApiResponse(code = 404, message = "Payment method not found", response = ErrorResponseDTO.class)
    })
    void delete(@ApiParam(value = "Payment method ID", example = "1", required = true) Long id);

}

package com.github.cenafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.request.OrderRequestDTO;
import com.github.cenafood.api.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.api.model.response.OrderCreatedResponseDTO;
import com.github.cenafood.api.model.response.OrderResponseDTO;
import com.github.cenafood.core.openapi.FieldsResponse;
import com.github.cenafood.domain.filter.OrderFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author elielcena
 *
 */
@Api(tags = "Orders")
public interface OrderControllerOpenApi {

    @FieldsResponse
    @ApiOperation("Search all orders with filters and pagination")
    PagedModel<OrderAbstractResponseDTO> findAllWithFilter(OrderFilter filter, Pageable pageable);

    @FieldsResponse
    @ApiOperation("Search orders by ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid order ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Order not found", response = ErrorResponseDTO.class)
    })
    OrderResponseDTO findByCode(@ApiParam(value = "Order code", example = "fcffa16d-d918-4deb-a3e6-cc46e096514", required = true) String code);

    @ApiOperation("Register a new order")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered order"),
    })
    OrderCreatedResponseDTO generate(@ApiParam(name = "body", value = "Representation of a new order", required = true) OrderRequestDTO orderRequest);

    @ApiOperation("Change the status of the order confirmed by code")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Order confirmed"),
        @ApiResponse(code = 404, message = "Order not found", response = ErrorResponseDTO.class)
    })
    ResponseEntity<Void> confirm(@ApiParam(value = "Order code", example = "fcffa16d-d918-4deb-a3e6-cc46e096514", required = true) String code);

    @ApiOperation("Change the status of the order delivered by code")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Order canceled"),
        @ApiResponse(code = 404, message = "Order not found", response = ErrorResponseDTO.class)
    })
    ResponseEntity<Void> delivery(@ApiParam(value = "Order code", example = "fcffa16d-d918-4deb-a3e6-cc46e096514", required = true) String code);

    @ApiOperation("Change the status of the order canceled by code")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Order canceled"),
        @ApiResponse(code = 404, message = "Order not found", response = ErrorResponseDTO.class)
    })
    ResponseEntity<Void> cancel(@ApiParam(value = "Order code", example = "fcffa16d-d918-4deb-a3e6-cc46e096514", required = true) String code);

}

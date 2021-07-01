package com.github.cenafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.v1.model.request.ProductRequestDTO;
import com.github.cenafood.api.v1.model.response.ProductResponseDTO;
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
@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {

    @FieldsResponse
    @ApiOperation("Search for products associated with restaurants")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid restaurant ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    CollectionModel<ProductResponseDTO> find(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id);

    @FieldsResponse
    @ApiOperation("Search for a product in a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid restaurant or product  ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant product not found", response = ErrorResponseDTO.class)
    })
    ProductResponseDTO findById(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Product ID", example = "1", required = true) Long idProduct);

    @ApiOperation("Register a new restaurant product")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Registered order"),
        @ApiResponse(code = 404, message = "Restaurant product not found", response = ErrorResponseDTO.class)
    })
    ProductResponseDTO save(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(name = "body", value = "Representation of a new restaurant product", required = true) ProductRequestDTO productRequest);

    @ApiOperation("Update restaurant product")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated product"),
        @ApiResponse(code = 404, message = "Restaurant product not found", response = ErrorResponseDTO.class)
    })
    ProductResponseDTO update(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Product ID", example = "1", required = true) Long idProduct,
            @ApiParam(name = "body", value = "Representation of a restaurant product with new data", required = true) ProductRequestDTO productRequest);
}

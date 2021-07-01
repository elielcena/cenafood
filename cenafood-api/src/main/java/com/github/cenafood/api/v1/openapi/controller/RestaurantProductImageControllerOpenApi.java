package com.github.cenafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.v1.model.request.ProductImageRequestDTO;
import com.github.cenafood.api.v1.model.response.ProductImageResponseDTO;
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
public interface RestaurantProductImageControllerOpenApi {

    @FieldsResponse
    @ApiOperation(value = "Search for products associated with restaurants by id image", produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid restaurant ID", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant not found", response = ErrorResponseDTO.class)
    })
    ProductImageResponseDTO findById(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Product ID", example = "1", required = true) Long idProduct,
            @ApiParam(value = "Image ID", example = "1", required = true) Long idImage);

    @ApiOperation(value = "Search for products associated with restaurants by id image", hidden = true)
    ResponseEntity<?> findProductImage(Long id, Long idProduct, Long idImage);

    @ApiOperation(value = "Update restaurant product image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Updated product image"),
        @ApiResponse(code = 404, message = "Restaurant product image not found", response = ErrorResponseDTO.class)
    })
    ProductImageResponseDTO updateImage(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Product ID", example = "1", required = true) Long idProduct,
            @ApiParam(name = "body", value = "Representation of a new restaurant product with new image",
                    required = true) ProductImageRequestDTO productImageRequest)
        throws IOException;

    @ApiOperation("Delete restaurant product image")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Deleted product image"),
        @ApiResponse(code = 400, message = "Invalid restaurant id or product id or image id", response = ErrorResponseDTO.class),
        @ApiResponse(code = 404, message = "Restaurant product image not found", response = ErrorResponseDTO.class)
    })
    void delete(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long id,
            @ApiParam(value = "Product ID", example = "1", required = true) Long idProduct,
            @ApiParam(name = "body", value = "Representation of a new restaurant product with new image", required = true) Long idImage);

}

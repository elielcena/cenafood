package com.github.cenafood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.ProductImageMapper;
import com.github.cenafood.api.model.request.ProductImageRequestDTO;
import com.github.cenafood.api.model.response.ProductImageResponseDTO;
import com.github.cenafood.api.openapi.controller.RestaurantProductImageControllerOpenApi;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.ProductImage;
import com.github.cenafood.domain.service.ImageStorageService.RecoveredImage;
import com.github.cenafood.domain.service.ProductImageService;
import com.github.cenafood.domain.service.ProductService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/restaurants/{id}/products/{idProduct}/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductImageController implements RestaurantProductImageControllerOpenApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductImageMapper mapper;

    @GetMapping(path = "/{idImage}")
    public ProductImageResponseDTO findById(@PathVariable Long id, @PathVariable Long idProduct, @PathVariable Long idImage) {
        Product product = productService.findById(idProduct, id);
        return mapper.toDTO(productImageService.findById(idImage, product));
    }

    @GetMapping(path = "/{idImage}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> findProductImage(@PathVariable Long id, @PathVariable Long idProduct, @PathVariable Long idImage) {
        try {
            Product product = productService.findById(idProduct, id);
            RecoveredImage recoveredImage = productImageService.findProductImage(idImage, product);
            return recoveredImage.getResponseEntity();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductImageResponseDTO updateImage(@PathVariable Long id, @PathVariable Long idProduct, @Valid ProductImageRequestDTO productImageRequest)
        throws IOException {
        Product product = productService.findById(idProduct, id);
        ProductImage productImage = ProductImage.fromProductImageRequestDTO(productImageRequest, product);
        productImageService.save(productImage, productImageRequest.getFile().getInputStream());
        return mapper.toDTO(productImage);
    }

    @DeleteMapping("/{idImage}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @PathVariable Long idProduct, @PathVariable Long idImage) {
        Product product = productService.findById(idProduct, id);
        productImageService.delete(idImage, product);
    }

}

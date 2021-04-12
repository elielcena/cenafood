package com.github.cenafood.domain.service;

import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.ProductImage;
import com.github.cenafood.domain.repository.ProductImageRepository;
import com.github.cenafood.domain.service.ImageStorageService.NewImage;
import com.github.cenafood.domain.service.ImageStorageService.RecoveredImage;

/**
 * @author elielcena
 *
 */
@Service
public class ProductImageService {

    private static final String MSG_RESOURCE_NOT_FOUND = "There is no Product Image register with code %d";

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    public ProductImage findById(Long id, Product product) {
        return productImageRepository.findById(id, product.getId(), product.getRestaurant().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
    }

    public RecoveredImage findProductImage(Long id, Product product) {
        ProductImage productImage = findById(id, product);

        return imageStorageService.recover(productImage.getFileName());
    }

    @Transactional
    public ProductImage save(ProductImage productImage, InputStream inputStream) {
        productImage.setFileName(imageStorageService.generateFileName(productImage.getFileName()));
        productImage = productImageRepository.save(productImage);
        productImageRepository.flush();

        NewImage newImage = NewImage.builder()
                .fileName(productImage.getFileName())
                .contentType(productImage.getContentType())
                .inputStream(inputStream)
                .build();

        imageStorageService.store(newImage);

        return productImage;
    }

    public void delete(Long id, Product product) {
        ProductImage productImage = findById(id, product);

        productImageRepository.delete(productImage);
        productImageRepository.flush();

        imageStorageService.delete(productImage.getFileName());
    }

}

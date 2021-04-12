package com.github.cenafood.domain.service;

import static org.apache.commons.lang3.BooleanUtils.isTrue;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.github.cenafood.core.storage.StorageProperties.StorageType;
import com.github.cenafood.infrastructure.service.storage.LocalImageStorageService;
import com.github.cenafood.infrastructure.service.storage.S3ImageStorageService;

import lombok.Builder;
import lombok.Data;

/**
 * @author elielcena
 *
 */
public interface ImageStorageService {

    RecoveredImage recover(String fileName);

    void store(NewImage newImage);

    void delete(String fileName);

    default String generateFileName(String originalName) {
        return UUID.randomUUID().toString().concat("_").concat(originalName);
    }

    static ImageStorageService getStorageImplementation(StorageType storageType) {
        if (StorageType.S3.equals(storageType))
            return new S3ImageStorageService();

        return new LocalImageStorageService();
    }

    @Builder
    @Data
    class NewImage {

        private String fileName;

        private String contentType;

        private InputStream inputStream;

    }

    @Builder
    @Data
    class RecoveredImage {

        private InputStream inputStream;

        private String url;

        public Boolean hasUrl() {
            return null != this.url;
        }

        public Boolean hasInputStream() {
            return null != this.inputStream;
        }

        public ResponseEntity<?> getResponseEntity() {
            if (isTrue(hasUrl())) {
                return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, getUrl()).build();
            } else {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(new InputStreamResource(getInputStream()));
            }
        }

    }

}

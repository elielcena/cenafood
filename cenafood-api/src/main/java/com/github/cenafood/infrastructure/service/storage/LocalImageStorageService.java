package com.github.cenafood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.github.cenafood.core.storage.StorageProperties;
import com.github.cenafood.domain.service.ImageStorageService;
import com.github.cenafood.infrastructure.service.storage.exception.StorageException;

/**
 * @author elielcena
 *
 */
@Service
public class LocalImageStorageService implements ImageStorageService {

    private static final String ERROR_SAVE_IMAGE_STORAGE = "Unable to store file";

    private static final String ERROR_DELETE_IMAGE_STORAGE = "Couldn't delete file";

    private static final String ERROR_RECOVER_IMAGE_STORAGE = "Couldn't retrieve file";

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void store(NewImage newImage) {
        Path path = getFilePath(newImage.getFileName());

        try {
            FileCopyUtils.copy(newImage.getInputStream(), Files.newOutputStream(path));
        } catch (Exception e) {
            throw new StorageException(ERROR_SAVE_IMAGE_STORAGE, e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            Path filePath = getFilePath(fileName);

            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException(ERROR_DELETE_IMAGE_STORAGE, e);
        }

    }

    @Override
    public RecoveredImage recover(String fileName) {
        try {
            Path arquivoPath = getFilePath(fileName);

            return RecoveredImage.builder().inputStream(Files.newInputStream(arquivoPath)).build();
        } catch (Exception e) {
            throw new StorageException(ERROR_RECOVER_IMAGE_STORAGE, e);
        }
    }

    private Path getFilePath(String fileName) {
        return storageProperties.getLocal().getPathImage().resolve(Path.of(fileName));
    }

}

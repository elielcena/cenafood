package com.github.cenafood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.github.cenafood.core.storage.StorageProperties;
import com.github.cenafood.domain.service.ImageStorageService;
import com.github.cenafood.infrastructure.service.exception.StorageException;

/**
 * @author elielcena
 *
 */
@Service
public class S3ImageStorageService implements ImageStorageService {

    private static final String ERROR_SAVE_IMAGE_STORAGE = "Unable to store file";

    private static final String ERROR_DELETE_IMAGE_STORAGE = "Couldn't delete file";

    private static final String ERROR_RECOVER_IMAGE_STORAGE = "Couldn't retrieve file";

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public void store(NewImage newImage) {
        try {
            String filePath = getFilePath(newImage.getFileName());

            var bytes = IOUtils.toByteArray(newImage.getInputStream());
            var metadata = new ObjectMetadata();
            metadata.setContentLength(bytes.length);
            metadata.setContentType(newImage.getContentType());

            var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getAwsBucket(), filePath, newImage.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new StorageException(ERROR_SAVE_IMAGE_STORAGE, e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            String filePath = getFilePath(fileName);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getAwsBucket(), filePath);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException(ERROR_DELETE_IMAGE_STORAGE, e);
        }
    }

    @Override
    public RecoveredImage recover(String fileName) {
        try {
            String filePath = getFilePath(fileName);

            URL url = amazonS3.getUrl(storageProperties.getS3().getAwsBucket(), filePath);

            return RecoveredImage.builder().url(url.toString()).build();
        } catch (Exception e) {
            throw new StorageException(ERROR_RECOVER_IMAGE_STORAGE, e);
        }
    }

    private String getFilePath(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getAwsPathImage(), fileName);
    }

}

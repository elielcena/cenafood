package com.github.cenafood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author elielcena
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties("cenafood.storage")
public class StorageProperties {

    private Local local = new Local();

    private S3 s3 = new S3();

    private StorageType storageType = StorageType.LOCAL;

    @Getter
    public enum StorageType {
        LOCAL,
        S3
    }

    @Getter
    @Setter
    public class Local {

        private Path pathImage;
    }

    @Getter
    @Setter
    public class S3 {

        private String awsAccessKeyId;

        private String awsSecretAccessKey;

        private String awsBucket;

        private String awsPathImage;

        private Regions awsRegion;
    }
}

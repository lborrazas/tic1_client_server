package tic1.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import tic1.server.storage.StorageProperties;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class AdditionalConfiguration {

    @Autowired
    private StorageProperties storageProperties;

    // make use of the bound properties
}
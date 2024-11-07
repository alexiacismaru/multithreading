package com.example.multithreading.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("threading")
public class ApplicationProperties {
    private final int recordCount;
    private final boolean forceSeedDatabase;

    @ConstructorBinding
    public ApplicationProperties(int recordCount, boolean forceSeedDatabase) {
        this.recordCount = recordCount;
        this.forceSeedDatabase = forceSeedDatabase;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public boolean isForceSeedDatabase() {
        return forceSeedDatabase;
    }
}


package org.config;

import org.aeonbits.owner.ConfigCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertyReader {

    private PropertyReader() {
        // Prevent instantiation
    }

    public static FrameworkConfig getConfig() {
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }

   // protected static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
}

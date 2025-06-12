package org.config;

import org.aeonbits.owner.ConfigCache;

public final class PropertyReader {

    private PropertyReader() {
        // Prevent instantiation
    }

    public static FrameworkConfig getConfig() {
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}

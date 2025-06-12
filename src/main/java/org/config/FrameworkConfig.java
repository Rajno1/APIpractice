package org.config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", // for command line
        "system:env",        // for environment variables
        "file:${user.dir}/src/test/resources/config/config.properties" // for local config
})
public interface FrameworkConfig extends Config {
    String baseUri();
    String employeeEndPoint();
}

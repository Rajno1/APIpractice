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

    @Key("jira.baseurl")
    String jiraBaseUrl();

    @Key("jira.createissue.endpoint")
    String jiraCreateIssueEndpoint();

    @Key("jira.email")
    String jiraEmail();

    @Key("jira.api.token")
    String jiraApiToken();

    @Key("jira.project.key")
    String jiraProjectKey();

}

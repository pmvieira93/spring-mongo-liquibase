package com.example;

import liquibase.command.CommandResults;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionCommandStep;
import liquibase.exception.LiquibaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class LiquibaseMongoDBConfig {

    static final Logger log = LoggerFactory.getLogger(LiquibaseMongoDBConfig.class);

    // Liquibase properties
    private static final String MONGODB_LIQUIBASE_URI = "mongodb://%s:%s@%s:%s/%s?authSource=admin";

    @Value("${spring.liquibase-mongodb.change-log:liquibase/changelog.xml}")
    private String liquibaseChangeLogFile;

    @Value("${spring.liquibase-mongodb.enabled:false}")
    private Boolean liquibaseEnabled;

    // MongoDB properties
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Value("${spring.data.mongodb.username}")
    private String mongoAdminUser;

    @Value("${spring.data.mongodb.password}")
    private String mongoAdminPassword;

    @Bean
    public CommandResults liquibaseUpdate() {
        if (liquibaseEnabled) {
            log.info("Liquibase is enabled");
            try {
                return new CommandScope("update")
                        .addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, liquibaseChangeLogFile)
                        .addArgumentValue(DbUrlConnectionCommandStep.URL_ARG, buildConnectionUrl())
                        .execute();
            } catch (LiquibaseException e) {
                log.error("error running liquibase {}", e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            log.info("Liquibase is disabled");
            return null;
        }
    }

    private String buildConnectionUrl() {
        return String.format(MONGODB_LIQUIBASE_URI, mongoAdminUser, mongoAdminPassword, mongoHost, mongoPort, mongoDatabase);
    }
}

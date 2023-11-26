package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.credentials;




import ch.qos.logback.classic.Logger;

import io.clue2solve.aws.bedrock.springboot.starter.config.AwsCredentialsProperties;
import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(name = "aws.credentials.provider", havingValue = "basic")
@EnableConfigurationProperties(AwsCredentialsProperties.class)
public class BasicAwsCredentialsConfig {
    Logger logger = (Logger) LoggerFactory.getLogger(BasicAwsCredentialsConfig.class);
    @Autowired
    private AwsCredentialsProperties properties;

    public BasicAwsCredentialsConfig() {
        logger.info("Instantiating BasicAwsCredentialsConfig");
    }

    @Bean
    @Primary
    public AwsCredentialsProvider awsCredentialsProvider() {
        logger.info("Creating StaticCredentialsProvider bean");
        AwsCredentialsProvider provider = StaticCredentialsProvider.create(AwsBasicCredentials.create(
            properties.getAccessKey(),
            properties.getSecretKey()
        ));
        logger.info("Created AwsCredentialsProvider bean: " + provider);
        return provider;
    }

    @PostConstruct
    public void postConstruct() {
    logger.info("AwsCredentialsProperties: " + properties);
}
}
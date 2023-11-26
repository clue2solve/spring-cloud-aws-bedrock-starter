package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.credentials;

import io.clue2solve.aws.bedrock.springboot.starter.config.AwsCredentialsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;

@Configuration
@ConditionalOnProperty(name = "aws.credentials.provider", havingValue = "environment")
@EnableConfigurationProperties(AwsCredentialsProperties.class)
public class EnvironmentAwsCredentialsConfig {

    @Bean
    @ConditionalOnMissingBean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return EnvironmentVariableCredentialsProvider.create();
    }
}
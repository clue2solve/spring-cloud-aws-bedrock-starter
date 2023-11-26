package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrock.BedrockAsyncClient;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeAsyncClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

@Configuration
public class BedrockClientConfig {

    private AwsCredentialsProvider awsCredentialsProvider;

    @Value("${aws.region}")
    private String region;

    @Autowired
    public BedrockClientConfig(AwsCredentialsProvider awsCredentialsProvider) {
        this.awsCredentialsProvider = awsCredentialsProvider;
    }

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "sync")
    public BedrockClient bedrockClient() {
        return BedrockClient.builder()
                .region(Region.of(region))
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    // @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "sync")
    public BedrockRuntimeClient bedrockRuntimeClient() {
        return BedrockRuntimeClient.builder()
                .region(Region.of(region))
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "async")
    public BedrockRuntimeAsyncClient bedrockRuntimeAsyncClient() {
        return BedrockRuntimeAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "async")
    public BedrockAsyncClient bedrockAsyncClient() {
        return BedrockAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider((AwsCredentialsProvider) awsCredentialsProvider)
                .build();
    }
}
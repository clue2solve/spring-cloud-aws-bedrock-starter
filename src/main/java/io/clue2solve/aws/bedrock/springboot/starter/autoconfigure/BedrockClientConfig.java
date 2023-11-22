package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure;

import com.amazonaws.auth.AWSCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrock.BedrockAsyncClient;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeAsyncClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BedrockClientConfig {

    @Autowired
    private AWSCredentialsProvider awsCredentialsProvider;

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "sync")
    public BedrockClient bedrockClient() {
        return BedrockClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider((AwsCredentialsProvider) awsCredentialsProvider)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "sync")
    public BedrockRuntimeClient bedrockRuntimeClient() {
        return BedrockRuntimeClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider((AwsCredentialsProvider) awsCredentialsProvider)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "async")
    public BedrockRuntimeAsyncClient bedrockRuntimeAsyncClient() {
        return BedrockRuntimeAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider((AwsCredentialsProvider) awsCredentialsProvider)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "bedrock.client.type", havingValue = "async")
    public BedrockAsyncClient bedrockAsyncClient() {
        return BedrockAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider((AwsCredentialsProvider) awsCredentialsProvider)
                .build();
    }
}
package io.clue2solve.aws.bedrock.springboot.starter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.credentials")
public record AwsCredentialsProperties(String accessKey, String secretKey, String sessionToken, String provider) {}

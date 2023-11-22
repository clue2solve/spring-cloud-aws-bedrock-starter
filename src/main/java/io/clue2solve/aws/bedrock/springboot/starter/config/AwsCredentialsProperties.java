package io.clue2solve.aws.bedrock.springboot.starter.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.credentials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AwsCredentialsProperties {

    private String accessKey;
    private String secretKey;
    private String sessionToken;
    private String provider; // "basic", "environment", or "session"

    // Getters and Setters
}

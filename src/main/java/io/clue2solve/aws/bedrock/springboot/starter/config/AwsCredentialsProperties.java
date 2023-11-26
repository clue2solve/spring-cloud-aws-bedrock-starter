package io.clue2solve.aws.bedrock.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "aws.credentials")
public class AwsCredentialsProperties {

    private String accessKey;
    private String secretKey;
    private String sessionToken;
    private String provider;

    // getters and setters
}
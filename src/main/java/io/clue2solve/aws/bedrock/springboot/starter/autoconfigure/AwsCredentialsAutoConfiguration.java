package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.clue2solve.aws.bedrock.springboot.starter.config.AwsCredentialsProperties;

@Configuration
@EnableConfigurationProperties(AwsCredentialsProperties.class)
public class AwsCredentialsAutoConfiguration {

    @Autowired
    private AwsCredentialsProperties awsCredentialsProperties;

}

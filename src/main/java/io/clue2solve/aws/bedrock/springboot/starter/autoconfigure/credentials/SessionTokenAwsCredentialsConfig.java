package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.credentials;

import io.clue2solve.aws.bedrock.springboot.starter.config.AwsCredentialsProperties;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aws.credentials.provider", havingValue = "session")
public class SessionTokenAwsCredentialsConfig {

    @Autowired
    private AwsCredentialsProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicSessionCredentials(
                properties.accessKey(),
                properties.secretKey(),
                properties.sessionToken()
        ));
    }
}
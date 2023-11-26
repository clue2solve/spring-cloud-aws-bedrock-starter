import io.clue2solve.aws.bedrock.springboot.starter.config.AwsCredentialsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;

@Configuration
@ConditionalOnProperty(name = "aws.credentials.provider", havingValue = "session")
@EnableConfigurationProperties(AwsCredentialsProperties.class)
public class SessionTokenAwsCredentialsConfig {

    private AwsCredentialsProperties properties;

    public SessionTokenAwsCredentialsConfig(AwsCredentialsProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider.create(AwsSessionCredentials.create(
                properties.getAccessKey(),
                properties.getSecretKey(),
                properties.getSessionToken()
        ));
    }
}
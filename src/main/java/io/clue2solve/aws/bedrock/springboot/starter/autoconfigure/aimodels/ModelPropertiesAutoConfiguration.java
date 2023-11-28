package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.ClaudeProperties;
import io.clue2solve.aws.bedrock.springboot.starter.config.JurassicProperties;
import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.config.TitanProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration for all model variant properties.
 */
@Configuration
@EnableConfigurationProperties({ ClaudeProperties.class, JurassicProperties.class, LlamaProperties.class,
		TitanProperties.class })
public class ModelPropertiesAutoConfiguration {

}

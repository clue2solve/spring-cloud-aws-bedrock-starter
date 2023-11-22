package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure;

import io.clue2solve.aws.bedrock.springboot.starter.config.ClaudeProperties;
import io.clue2solve.aws.bedrock.springboot.starter.config.JurassicProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ClaudeProperties.class, JurassicProperties.class})
public class ModelPropertiesAutoConfiguration {}

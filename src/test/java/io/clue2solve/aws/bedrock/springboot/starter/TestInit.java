package io.clue2solve.aws.bedrock.springboot.starter;

import io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels.BedrockAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(BedrockAutoConfiguration.class)
public class TestInit {

}

package io.clue2solve.aws.bedrock.springboot.starter.config;



import org.springframework.boot.context.properties.ConfigurationProperties;

/**Sample perperties
 * aws.bedrock.model.jurassic.modelId=arn:aws:sagemaker:us-east-1:123456789012:model/jurassic-park
 * aws.bedrock.model.jurassic.prompt=Human: Assistant:
 * aws.bedrock.model.jurassic.maxTokens=200
 * aws.bedrock.model.jurassic.temperature=0.5
 */
@ConfigurationProperties(prefix = "aws.bedrock.model.jurassic")
public record JurassicProperties(String modelId, String prompt, int maxTokens, double temperature) {}


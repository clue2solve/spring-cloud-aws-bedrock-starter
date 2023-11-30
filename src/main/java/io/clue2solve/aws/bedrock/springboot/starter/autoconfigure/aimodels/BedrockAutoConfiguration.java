package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.*;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.impl.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrock.BedrockAsyncClient;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeAsyncClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

/**
 * Auto-configuration for all model variant properties.
 */
@AutoConfiguration
@EnableConfigurationProperties({ ClaudeProperties.class, JurassicProperties.class, LlamaProperties.class,
		StableDiffusionProperties.class, TitanProperties.class })
public class BedrockAutoConfiguration {

	private static Logger log = LoggerFactory.getLogger(BedrockAutoConfiguration.class);

	/**
	 * Bean for BedrockClient
	 * @return BedrockClient
	 */
	@Bean
	@ConditionalOnProperty(name = "bedrock.client.type", havingValue = "sync", matchIfMissing = true)
	public BedrockClient bedrockClient(AwsCredentialsProvider awsCredentialsProvider,
			@Value("${aws.region}") String region) {
		return BedrockClient.builder().region(Region.of(region)).credentialsProvider(awsCredentialsProvider).build();
	}

	/**
	 * Bean for BedrockRuntimeClient
	 * @return BedrockRuntimeClient
	 */
	@Bean
	@ConditionalOnProperty(name = "bedrock.client.type", havingValue = "sync", matchIfMissing = true)
	public BedrockRuntimeClient bedrockRuntimeClient(AwsCredentialsProvider awsCredentialsProvider,
			@Value("${aws.region}") String region) {
		return BedrockRuntimeClient.builder()
			.region(Region.of(region))
			.credentialsProvider(awsCredentialsProvider)
			.build();
	}

	/**
	 * Bean for BedrockRuntimeAsyncClient
	 * @return BedrockRuntimeAsyncClient
	 */
	@Bean
	@ConditionalOnProperty(name = "bedrock.client.type", havingValue = "async")
	public BedrockRuntimeAsyncClient bedrockRuntimeAsyncClient(AwsCredentialsProvider awsCredentialsProvider,
			@Value("${aws.region}") String region) {
		return BedrockRuntimeAsyncClient.builder()
			.region(Region.of(region))
			.credentialsProvider(awsCredentialsProvider)
			.build();
	}

	/**
	 * Bean for BedrockAsyncClient
	 * @return BedrockAsyncClient
	 */
	@Bean
	@ConditionalOnProperty(name = "bedrock.client.type", havingValue = "async")
	public BedrockAsyncClient bedrockAsyncClient(AwsCredentialsProvider awsCredentialsProvider,
			@Value("${aws.region}") String region) {
		return BedrockAsyncClient.builder()
			.region(Region.of(region))
			.credentialsProvider(awsCredentialsProvider)
			.build();
	}

	/**
	 * Bean for ClaudeService
	 * @param client BedrockRuntimeClient
	 * @param properties ClaudeProperties
	 * @return BedrockService
	 */
	@Bean(name = "claudeService")
	@Conditional(OnClaude.class)
	public BedrockService claudeService(BedrockRuntimeClient client, ClaudeProperties properties) {
		return new ClaudeService(client, properties);
	}

	/**
	 * Bean for JurassicService
	 * @param client BedrockRuntimeClient
	 * @param properties JurassicProperties
	 * @return BedrockService
	 */
	@Bean(name = "jurassicService")
	@Conditional(OnJurassic.class)
	public BedrockService jurassicService(BedrockRuntimeClient client, JurassicProperties properties) {
		return new JurassicService(client, properties);
	}

	/**
	 * Bean for LlamaService
	 * @param client BedrockRuntimeClient
	 * @param properties LlamaProperties
	 * @return BedrockService
	 */
	@Bean(name = "llamaService")
	@ConditionalOnProperty(name = "aws.bedrock.model.llama.id", havingValue = "meta.llama2-13b-chat-v1")
	public BedrockService llamaService(BedrockRuntimeClient client, LlamaProperties properties) {
		return new LlamaService(client, properties);
	}

	/**
	 * Bean for StableDiffusionService
	 * @param client BedrockRuntimeClient
	 * @param properties StableDiffusionProperties
	 * @return BedrockService
	 */
	@Bean(name = "stableDiffusionService")
	@Conditional(OnStableDiffusion.class)
	public BedrockService stableDiffusionService(BedrockRuntimeClient client, StableDiffusionProperties properties) {
		return new StableDiffusionService(client, properties);
	}

	/**
	 * Bean for TitanService
	 * @param client BedrockRuntimeClient
	 * @param properties TitanProperties
	 * @return BedrockService
	 */
	@Bean(name = "titanService")
	@Conditional(OnTitan.class)
	public BedrockService titanService(BedrockRuntimeClient client, TitanProperties properties) {
		return new TitanService(client, properties);
	}

}

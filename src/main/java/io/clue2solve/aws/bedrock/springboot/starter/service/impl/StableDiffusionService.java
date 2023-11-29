package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.config.StableDiffusionProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

/**
 * Service class for Stable Diffusion - Implementation of BedrockService
 */
public class StableDiffusionService implements BedrockService {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(StableDiffusionService.class);

	/**
	 * BedrockRuntimeClient
	 */
	private final BedrockRuntimeClient client;

	/**
	 * StableDiffusionProperties
	 */
	private final StableDiffusionProperties properties;

	/**
	 * Constructor
	 * @param client BedrockRuntimeClient
	 * @param properties StableDiffusionProperties
	 */
	public StableDiffusionService(BedrockRuntimeClient client, StableDiffusionProperties properties) {
		this.client = client;
		this.properties = properties;
		log.info("Instantiating StableDiffusionService");
	}

	/**
	 * Invoke the model
	 * @param prompt String
	 * @return String
	 * @throws JsonProcessingException JsonProcessingException
	 */
	@Override
	public String invoke(String prompt) throws JsonProcessingException {
		try {
			var mapper = new ObjectMapper();
			var payload = mapper.createObjectNode();

			var prompts = payload.putObject("text_prompts");
			prompts.put("text", prompt);

			payload.put("cfg_scale", properties.promptStrength());
			payload.put("steps", properties.generationStep());
			payload.put("seed", properties.seed());

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.id()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = mapper.readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("generation").asText();
		}
		catch (AwsServiceException ase) {
			log.error("Failed to obtain result from StableDiffusionService", ase);
		}
		return null;
	}

}
package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.clue2solve.aws.bedrock.springboot.starter.config.JurassicProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Service class for Jurassic - Implementation of BedrockService
 */
public class JurassicService implements BedrockService {

	/** Logger */
	private static final Logger log = LoggerFactory.getLogger(JurassicService.class);

	/**
	 * BedrockRuntimeClient
	 */
	private final BedrockRuntimeClient client;

	/**
	 * JurassicProperties
	 */
	private final JurassicProperties properties;

	/**
	 * Constructor
	 * @param client
	 * @param properties
	 */
	public JurassicService(BedrockRuntimeClient client, JurassicProperties properties) {
		this.client = client;
		this.properties = properties;
		log.info("Instantiating JurassicService");
	}

	/**
	 * Invoke the model
	 * @param prompt
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public String invoke(String prompt) throws JsonProcessingException {
		try {
			var mapper = new ObjectMapper();
			var payload = mapper.createObjectNode();
			payload.put("prompt", String.format("%s%n%s", properties.prePrompt(), prompt));
			payload.put("maxTokens", properties.maxTokens());
			payload.put("temperature", properties.temperature());

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.id()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = mapper.readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("completions").elements().next().get("data").get("text").asText();
		}
		catch (AwsServiceException ase) {
			log.error("Failed to obtain result from JurassicService", ase);
		}
		return null;
	}

}
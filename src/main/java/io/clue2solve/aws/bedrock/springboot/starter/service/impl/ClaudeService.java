package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.clue2solve.aws.bedrock.springboot.starter.config.ClaudeProperties;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Service class for Claude - Implementation of BedrockService
 */
public class ClaudeService implements BedrockService {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(ClaudeService.class);

	/**
	 * BedrockRuntimeClient
	 */
	private final BedrockRuntimeClient client;

	/**
	 * ClaudeProperties
	 */
	private final ClaudeProperties properties;

	/**
	 * Constructor
	 * @param client BedrockRuntimeClient
	 * @param properties ClaudeProperties
	 */
	public ClaudeService(BedrockRuntimeClient client, ClaudeProperties properties) {
		this.client = client;
		this.properties = properties;
		log.info("Instantiating ClaudeService");
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
			payload.put("prompt", String.format("%s%n%s%n%s", properties.prePrompt(), prompt, properties.postPrompt()));
			payload.put("max_tokens_to_sample", properties.maxTokens());
			payload.put("temperature", properties.temperature());
			// Create an ArrayNode and add elements to it
			var stopSequencesNode = mapper.createArrayNode();
			properties.stopSequences().forEach(stopSequencesNode::add);
			payload.set("stop_sequences", stopSequencesNode);

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.id()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = mapper.readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("completion").asText();
		}
		catch (AwsServiceException ase) {
			log.error("Failed to obtain result from ClaudeService", ase);
		}
		return null;
	}

}
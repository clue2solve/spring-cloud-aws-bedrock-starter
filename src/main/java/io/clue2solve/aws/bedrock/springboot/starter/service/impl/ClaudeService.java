package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.clue2solve.aws.bedrock.springboot.starter.config.ClaudeProperties;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ClaudeService implements BedrockService {

	private static final Logger logger = LoggerFactory.getLogger(ClaudeService.class);

	private final BedrockRuntimeClient client;

	private final ClaudeProperties properties;

	public ClaudeService(BedrockRuntimeClient client, ClaudeProperties properties) {
		this.client = client;
		this.properties = properties;
		logger.info("Instantiating ClaudeService");
	}

	@Override
	public String invoke(String prompt) throws JsonProcessingException {
		try {
			var enclosedPrompt = "Human: " + prompt + "\n\nAssistant:";
			var mapper = new ObjectMapper();
			var payload = mapper.createObjectNode();
			payload.put("prompt", enclosedPrompt);
			payload.put("max_tokens_to_sample", properties.maxTokensToSample());
			payload.put("temperature", properties.temperature());
			// Create an ArrayNode and add elements to it
			var stopSequencesNode = mapper.createArrayNode();
			properties.stopSequences().forEach(stopSequencesNode::add);
			payload.set("stop_sequences", stopSequencesNode);

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.modelId()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = new ObjectMapper().readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("completion").asText();
		}
		catch (AwsServiceException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
		return null;
	}

}
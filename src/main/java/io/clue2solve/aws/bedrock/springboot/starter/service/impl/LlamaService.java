package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

public class LlamaService implements BedrockService {

	private final BedrockRuntimeClient client;

	private final LlamaProperties properties;

	public LlamaService(BedrockRuntimeClient client, LlamaProperties properties) {
		this.client = client;
		this.properties = properties;
	}

	@Override
	public String invoke(String prompt) throws JsonProcessingException {
		try {
			var mapper = new ObjectMapper();
			var payload = mapper.createObjectNode();
			payload.put("prompt", properties.prompt() + prompt);
			payload.put("maxTokens", properties.maxTokens());
			payload.put("temperature", properties.temperature());

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.modelId()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = new ObjectMapper().readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("completions").elements().next().get("data").get("text").asText();
		}
		catch (AwsServiceException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
		return null;
	}

	// Add methods to interact with the llama model here

}
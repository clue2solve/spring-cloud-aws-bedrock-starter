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
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode payload = mapper.createObjectNode();
			payload.put("prompt", properties.prompt() + prompt);
			payload.put("maxTokens", properties.maxTokens());
			payload.put("temperature", properties.temperature());

			SdkBytes body = SdkBytes.fromUtf8String(payload.toString());

			InvokeModelRequest request = InvokeModelRequest.builder().modelId(properties.modelId()).body(body).build();

			InvokeModelResponse response = client.invokeModel(request);

			ObjectNode responseBody = new ObjectMapper().readValue(response.body().asUtf8String(), ObjectNode.class);

			String completion = responseBody.get("completions").elements().next().get("data").get("text").asText();

			return completion;

		}
		catch (AwsServiceException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
		return null;
	}

	// Add methods to interact with the llama model here

}
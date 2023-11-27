package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

public class LlamaService implements BedrockService {

	private static final Logger log = LoggerFactory.getLogger(LlamaService.class);

	private final BedrockRuntimeClient client;

	private final LlamaProperties properties;

	public LlamaService(BedrockRuntimeClient client, LlamaProperties properties) {
		this.client = client;
		this.properties = properties;
		log.info("Instantiating LlamaService");
	}

	@Override
	public String invoke(String prompt) throws JsonProcessingException {
		try {
			var mapper = new ObjectMapper();
			var payload = mapper.createObjectNode();
			payload.put("prompt", prompt);
			payload.put("max_gen_len", properties.maxTokens());
			payload.put("top_p", properties.topP());
			payload.put("temperature", properties.temperature());

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.id()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = mapper.readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("generation").asText();
		}
		catch (AwsServiceException ase) {
			log.error("Failed to obtain result from LlamaService", ase);
		}
		return null;
	}

}
package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.clue2solve.aws.bedrock.springboot.starter.config.TitanProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

public class TitanService implements BedrockService {

	private static final Logger log = LoggerFactory.getLogger(TitanService.class);

	private final BedrockRuntimeClient client;

	private final TitanProperties properties;

	public TitanService(BedrockRuntimeClient client, TitanProperties properties) {
		this.client = client;
		this.properties = properties;
		log.info("Instantiating TitanService");
	}

	@Override
	public String invoke(String prompt) throws JsonProcessingException {
		try {
			var mapper = new ObjectMapper();

			var payload = mapper.createObjectNode();
			payload.put("inputText", prompt);

			var textGenConfig = payload.putObject("textGenerationConfig");
			textGenConfig.put("maxTokenCount", properties.maxTokens());
			textGenConfig.put("temperature", properties.temperature());
			textGenConfig.put("topP", properties.topP());

			// Create an ArrayNode and add elements to it
			var stopSequencesNode = mapper.createArrayNode();
			properties.stopSequences().forEach(stopSequencesNode::add);
			textGenConfig.set("stopSequences", stopSequencesNode);

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.id()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = mapper.readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("results").elements().next().get("outputText").asText();
		}
		catch (AwsServiceException ase) {
			log.error("Failed to obtain result from TitanService", ase);
		}
		return null;
	}

}
package io.clue2solve.aws.bedrock.springboot.starter.service;


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

public class ClaudeService {

    private final BedrockRuntimeClient client;
    private final ClaudeProperties properties;

    public ClaudeService(BedrockRuntimeClient client, ClaudeProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    public String invokeClaude(String prompt) throws JsonProcessingException {
        try {
            String enclosedPrompt = "Human: " + prompt + "\n\nAssistant:";
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode payload = mapper.createObjectNode();
            payload.put("prompt", enclosedPrompt);
            payload.put("max_tokens_to_sample", properties.maxTokensToSample());
            payload.put("temperature", properties.temperature());
            // Create an ArrayNode and add elements to it
            ArrayNode stopSequencesNode = mapper.createArrayNode();
            properties.stopSequences().forEach(stopSequencesNode::add);
            payload.set("stop_sequences", stopSequencesNode);

            SdkBytes body = SdkBytes.fromUtf8String(payload.toString());

            InvokeModelRequest request = InvokeModelRequest.builder()
                    .modelId(properties.modelId())
                    .body(body)
                    .build();

            InvokeModelResponse response = client.invokeModel(request);

            ObjectNode responseBody = new ObjectMapper().readValue(response.body().asUtf8String(), ObjectNode.class);

            return responseBody.get("completion").asText();

        } catch (AwsServiceException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }
}
package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.config.StableDiffusionProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.ImageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Service class for Stable Diffusion - Implementation of BedrockService
 */
public class StableDiffusionService implements BedrockService, ImageService {

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

			var prompts = payload.putArray("text_prompts");
			var text = mapper.createObjectNode();
			text.put("text", prompt);
			prompts.add(text);

			payload.put("cfg_scale", properties.promptStrength());
			payload.put("steps", properties.generationStep());
			payload.put("seed", properties.seed());

			var body = SdkBytes.fromUtf8String(payload.toString());

			var request = InvokeModelRequest.builder().modelId(properties.id()).body(body).build();

			var response = client.invokeModel(request);

			var responseBody = mapper.readValue(response.body().asUtf8String(), ObjectNode.class);

			return responseBody.get("artifacts").elements().next().get("base64").asText();
		}
		catch (AwsServiceException ase) {
			log.error("Failed to obtain result from StableDiffusionService", ase);
		}
		return null;
	}

	@Override
	public void render(String imageString) {
		try {
			// obtain the image from encoded String
			var imageByte = Base64.decodeBase64(imageString);
			var bis = new ByteArrayInputStream(imageByte);
			BufferedImage image = ImageIO.read(bis);
			// write the image to a file
			File outputfile = new File(String.format("%s%s%s-%s.%s", System.getProperty("java.io.tmpdir"),
					System.getProperty("file.separator"), "image", RandomStringUtils.randomAlphanumeric(8), "png"));
			ImageIO.write(image, "png", outputfile);
		}
		catch (IOException ioe) {
			log.error("Could not decode base64-encoded imageString and/or subsequently store image to disk");
		}
	}

}
package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels.BedrockAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@TestPropertySource(
		properties = { "aws.region=us-west-2", "aws.bedrock.model.stability.id=stability.stable-diffusion-xl" })
@EnabledIf(
		expression = "#{environment.getActiveProfiles().length > 0 && {'authorized'}.contains(environment.getActiveProfiles()[0])}")

@DisabledIf(
		expression = "#{!environment.containsProperty('AWS_ACCESS_KEY_ID') || !environment.containsProperty('AWS_SECRET_ACCESS_KEY')}",
		reason = "Must have AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY environment variables defined to execute this test!")
class StableDiffusionServiceTest {

	private final static Logger log = LoggerFactory.getLogger(StableDiffusionServiceTest.class);

	@Autowired
	private StableDiffusionService service;

	@Test
	void invokeHappyPath() {
		try {
			String response = service.invoke(
					"Draw me a cartoon-like whale-shark in neon blue with bright yellow spots floating in the ocean of the cosmos on its way to meet its mate.");
			Assertions.assertNotNull(response);
			log.info("Encoded imageString: {}", response);
			service.render(response);
			log.info("Look for image in {}", System.getProperty("java.io.tmpdir"));
		}
		catch (JsonProcessingException jpe) {
			fail("Couldn't process request");
		}
	}

}
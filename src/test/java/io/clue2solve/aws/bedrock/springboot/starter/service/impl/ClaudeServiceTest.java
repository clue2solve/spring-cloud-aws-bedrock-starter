package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels.BedrockAutoConfiguration;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Service class for Claude - Implementation of BedrockService
 */
@SpringBootTest
@TestPropertySource(properties = { "aws.region=us-west-2", "aws.bedrock.model.claude.id=anthropic.claude-v2" })
@EnabledIf(
		expression = "#{environment.getActiveProfiles().length > 0 && {'authorized'}.contains(environment.getActiveProfiles()[0])}")
@DisabledIf(
		expression = "#{!environment.containsProperty('AWS_ACCESS_KEY_ID') || !environment.containsProperty('AWS_SECRET_ACCESS_KEY')}",
		reason = "Must have AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY environment variables defined to execute this test!")
class ClaudeServiceTest {

	private final static Logger log = LoggerFactory.getLogger(ClaudeServiceTest.class);

	@Autowired
	private ClaudeService service;

	@Test
	void invokeHappyPath() {
		try {
			String response = service.invoke(
					"Please compare and contrast the qualities and characteristics of a Bengal tiger with a snow leopard");
			Assertions.assertNotNull(response);
			log.info(response);
		}
		catch (JsonProcessingException jpe) {
			fail("Couldn't process request");
		}
	}

}
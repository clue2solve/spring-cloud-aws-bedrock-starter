package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@TestPropertySource(properties = { "aws.region=us-west-2", "aws.bedrock.model.llama.id=meta.llama2-13b-chat-v1" })
@EnabledIf(
		expression = "#{environment.getActiveProfiles().length > 0 && {'authorized'}.contains(environment.getActiveProfiles()[0])}",
		loadContext = true)
class LlamaServiceTest {

	private final static Logger log = LoggerFactory.getLogger(LlamaServiceTest.class);

	@Autowired
	private LlamaService service;

	@Test
	void invokeHappyPath() {
		try {
			String response = service.invoke(
					"Please list the Heisman Trophy winners (name, age, university attended, and year each individual won the award) for the years between 1980 and present in ascending order (by year).");
			Assertions.assertNotNull(response);
			log.info(response);
		}
		catch (JsonProcessingException jpe) {
			fail("Couldn't process request");
		}
	}

}
package io.clue2solve.aws.bedrock.springboot.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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
@TestPropertySource(properties = { "aws.region=us-west-2", "aws.bedrock.model.jurassic.id=ai21.j2-mid-v1" })
@EnabledIf(
		expression = "#{environment.getActiveProfiles().length > 0 && {'authorized'}.contains(environment.getActiveProfiles()[0])}",
		loadContext = true)
@DisabledIf(
		expression = "#{!environment.containsProperty('AWS_ACCESS_KEY_ID') || !environment.containsProperty('AWS_SECRET_ACCESS_KEY')}",
		reason = "Must have AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY environment variables defined to execute this test!")
class JurassicServiceTest {

	private final static Logger log = LoggerFactory.getLogger(JurassicServiceTest.class);

	@Autowired
	private JurassicService service;

	@Test
	void invokeHappyPath() {
		try {
			String response = service.invoke(
					"Commentate in a dark, sinister but humourous way a hypothetical 3 round fighting match between two DC Comics characters Superman and Darkside");
			Assertions.assertNotNull(response);
			log.info(response);
		}
		catch (JsonProcessingException jpe) {
			fail("Couldn't process request");
		}
	}

}
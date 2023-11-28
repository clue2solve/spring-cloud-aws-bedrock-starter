package io.clue2solve.aws.bedrock.springboot.starter.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Service interface for Bedrock
 */
public interface BedrockService {

	/**
	 * Invoke the model
	 * @param prompt String
	 * @return String
	 * @throws JsonProcessingException JsonProcessingException
	 */
	String invoke(String prompt) throws JsonProcessingException;

}
package io.clue2solve.aws.bedrock.springboot.starter.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface BedrockService {

	String invoke(String prompt) throws JsonProcessingException;

}
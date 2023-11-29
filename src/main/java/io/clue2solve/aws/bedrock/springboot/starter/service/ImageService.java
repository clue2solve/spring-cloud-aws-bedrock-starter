package io.clue2solve.aws.bedrock.springboot.starter.service;

public interface ImageService {

	/**
	 * Render an image It's up to implementation(s) how and where an image is written
	 * @param imageString the base64-encoded image string
	 */
	void render(String imageString);

}

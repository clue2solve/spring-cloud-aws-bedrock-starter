package io.clue2solve.aws.bedrock.springboot.starter.service;

public interface ImageService {

	/**
	 * Render and image It's up to implementations how and where an image is written
	 * @param imageString the base64-encoded image string
	 */
	void render(String imageString);

}

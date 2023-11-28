package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Condition class for Jurassic
 */
class OnJurassic extends AnyNestedCondition {

	/**
	 * Constructor
	 */
	OnJurassic() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	/**
	 * Condition for Jurassic ai21.j2-grande-instruct
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-grande-instruct")
	static class OnJurassic2GrandeInstruct {

	}

	/**
	 * Condition for Jurassic ai21.j2-jumbo-instruct
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-jumbo-instruct")
	static class OnJurassic2JumboInstruct {

	}

	/**
	 * Condition for Jurassic Instant ai21.j2-mid
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-mid")
	static class OnJurassic2Mid {

	}

	/**
	 * Condition for Jurassic Instant ai21.j2-mid-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-mid-v1")
	static class OnJurassic2MidV1 {

	}

	/**
	 * Condition for Jurassic Instant ai21.j2-ultra
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-ultra")
	static class OnCJurassic2Ultra {

	}

	/**
	 * Condition for Jurassic Instant ai21.j2-ultra-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.idd", havingValue = "ai21.j2-ultra-v1")
	static class OnCJurassic2UltraV1 {

	}

}
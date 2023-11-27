package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

class OnJurassic extends AnyNestedCondition {

	OnJurassic() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-grande-instruct")
	static class OnJurassic2GrandeInstruct {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-jumbo-instruct")
	static class OnJurassic2JumboInstruct {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-mid")
	static class OnJurassic2Mid {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-mid-v1")
	static class OnJurassic2MidV1 {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.id", havingValue = "ai21.j2-ultra")
	static class OnCJurassic2Ultra {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.jurassic.idd", havingValue = "ai21.j2-ultra-v1")
	static class OnCJurassic2UltraV1 {

	}

}
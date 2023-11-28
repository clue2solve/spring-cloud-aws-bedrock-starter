package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Condition class for Claude
 */
class OnClaude extends AnyNestedCondition {

	/**
	 * Constructor
	 */
	OnClaude() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	/**
	 * Condition for Claude anthropic.claude-v2
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.claude.id", havingValue = "anthropic.claude-v2")
	static class OnClaudeV2 {

	}

	/**
	 * Condition for Claude anthropic.claude-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.claude.id", havingValue = "anthropic.claude-v1")
	static class OnClaudeV1 {

	}

	/**
	 * Condition for Claude Instant anthropic.claude-instant-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.claude.id", havingValue = "anthropic.claude-instant-v1")
	static class OnClaudeInstantV1 {

	}

}
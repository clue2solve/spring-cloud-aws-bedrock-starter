# Spring Cloud AWS Bedrock Starter 
[![Alpha](https://img.shields.io/badge/Release-Alpha-darkred)](https://img.shields.io/badge/Release-Alpha-darkred) ![Github Action CI Workflow Status](https://github.com/clue2solve/aws-bedrock-springtboot-starter/actions/workflows/ci.yml/badge.svg) [![Known Vulnerabilities](https://snyk.io/test/github/clue2solve/aws-bedrock-springtboot-starter/badge.svg?style=plastic)](https://snyk.io/test/github/clue2solve/aws-bedrock-springtboot-starter) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

> Consider renaming project to `spring-cloud-aws-bedrock-starter`!

```properties
# For BasicAwsCredentialsConfig
spring.cloud.aws.credentials.access-key=YOUR_ACCESS_KEY
spring.cloud.aws.credentials.secret-key=YOUR_SECRET_KEY
spring.cloud.aws.credentials.profile.name=YOUR_PROFILE
spring.cloud.aws.credentials.profile.path=~/.aws/credentials

See the [Credentials](https://docs.awspring.io/spring-cloud-aws/docs/3.0.3/reference/html/index.html#credentials) section of Spring Cloud AWS Starter documentation for alternate configuration options.
```

### Properties that will enable ClaudeService
```properties
aws.bedrock.model.id=anthropic.claude-v2
aws.bedrock.model.claude.prompt=defaultPrompt
aws.bedrock.model.claude.temperature=0.5
aws.bedrock.model.claude.topP=0.5
aws.bedrock.model.claude.topK=50
aws.bedrock.model.claude.maxTokensToSample=100
```

### Jurasic Model Params
```properties 
aws.bedrock.model.id=jurassic2
aws.bedrock.model.jurassic.modelId=ai21.j2-mid-v1
aws.bedrock.model.jurassic.prompt=Your prompt here
aws.bedrock.model.jurassic.maxTokens=200
aws.bedrock.model.jurassic.temperature=0.5
```

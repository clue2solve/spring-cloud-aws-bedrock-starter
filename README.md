# Spring Cloud AWS Bedrock Starter
## Lets you use the AWS Bedrock Service with Spring Boot and Spring Cloud

[![Alpha](https://img.shields.io/badge/Release-Alpha-darkred)](https://img.shields.io/badge/Release-Alpha-darkred) ![Github Action CI Workflow Status](https://github.com/clue2solve/aws-bedrock-springtboot-starter/actions/workflows/ci.yml/badge.svg) [![Known Vulnerabilities](https://snyk.io/test/github/clue2solve/aws-bedrock-springtboot-starter/badge.svg?style=plastic)](https://snyk.io/test/github/clue2solve/aws-bedrock-springtboot-starter) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Bedrock 
This project provides a developer friendly way to access the services of [AWS Bedrock](https://aws.amazon.com/bedrock/), an AWS service that provides a managed Language Model (LLM) service with a catalog of supported LLMs.
This is a [Spring Cloud](https://spring.io/projects/spring-cloud) Starter for the Bedrock service that removes all the boilerplate code required to access the service. It uses the standard [Spring Cloud AWS](https://docs.awspring.io/spring-cloud-aws/docs/3.0.3/reference/html/index.html) Starter to provide the credentials for the [AWS SDK for Java](https://aws.amazon.com/sdk-for-java/), and the standards of Spring Boot and Spring Cloud to provide the configuration and the auto-wiring of the service objects.

#### This project leverages Spring Cloud AWS for credential management 

Just provide the right properties as defined below and the appropriate [AWSCredentialsProvider](https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/AWSCredentialsProvider.html) object will be activated for injection into your Spring objects. An example for the creation of [BasicAwsCredentials](https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/BasicAWSCredentials.html) is shown below. You can follow the link below to understand all the other supported credential models. 

```properties
# For BasicAwsCredentialsConfig
spring.cloud.aws.credentials.access-key=YOUR_ACCESS_KEY
spring.cloud.aws.credentials.secret-key=YOUR_SECRET_KEY
spring.cloud.aws.credentials.profile.name=YOUR_PROFILE
spring.cloud.aws.credentials.profile.path=~/.aws/credentials
```
> See the [Credentials](https://docs.awspring.io/spring-cloud-aws/docs/3.0.3/reference/html/index.html#credentials) section of Spring Cloud AWS Starter documentation for alternate configuration options.

### Properties that will enable ClaudeService

```properties
aws.bedrock.model.id=anthropic.claude-v2
aws.bedrock.model.claude.prompt=defaultPrompt
aws.bedrock.model.claude.temperature=0.5
aws.bedrock.model.claude.topP=0.5
aws.bedrock.model.claude.topK=50
aws.bedrock.model.claude.maxTokensToSample=100
```

### The Bedrock and BedrockRuntimeClient

Once the right AWSCredentialsProvider is injected, you can expect the activation of the appropriate versions of the [BedrockClient](https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/services/bedrock/BedrockClient.html) and the [BedrockRuntimeClient](https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/services/bedrockruntime/BedrockRuntimeClient.html). These will be conditional on the `bedrock.client.type` property. The options there are `sync` and `async`, which drive the activation of the _synchronous_ or the _asynchronous_ versions of the clients. 

E.g.,

```property
bedrock.client.type=sync
```

One can use the activated clients to interact with the AWS Bedrock service based on the docs for the Bedrock Service. 

### Bedrock Service

The best part of the Starter is the activation of a service object which has the `invoke` method.

All you need to do is provide the properties like one one of the sets below to activate the appropriate implementations of the BedrockService Interface ( Claude, Jurasic or Llama).

#### Claude model parameters

```properties
aws.bedrock.model.id=anthropic.claude-v2

aws.bedrock.model.claude.prompt=defaultPrompt
aws.bedrock.model.claude.temperature=0.5
aws.bedrock.model.claude.topP=0.5
aws.bedrock.model.claude.topK=50
aws.bedrock.model.claude.maxTokensToSample=100
```

#### Jurassic model parameters

```properties 
aws.bedrock.model.id=ai21.j2-mid-v1

aws.bedrock.model.jurassic.prompt=Your prompt here
aws.bedrock.model.jurassic.maxTokens=200
aws.bedrock.model.jurassic.temperature=0.5
```

#### Llama2 model parameters

```properties
aws.bedrock.model.id=anthropic.llama2-v1

aws.bedrock.model.llama2.prompt=Your prompt here
aws.bedrock.model.llama2.maxTokens=200
aws.bedrock.model.llama2.temperature=0.5
```

### Usage

Once activated, the Service can be autowired and used as below.

```java
private final ClaudeService claudeService;

@Autowired
public ClaudeController(ClaudeService claudeService) {
    this.claudeService = claudeService;
}

@GetMapping("/invoke")
public String invokeClaude(@RequestParam String prompt) {
    try {
        return claudeService.invokeClaude(prompt);
    } catch (Exception e) {
        return "Error invoking Claude: " + e.getMessage();
    }
}
```
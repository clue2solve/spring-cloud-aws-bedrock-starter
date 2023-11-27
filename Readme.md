# Aws Bedrock Springboot Starter
### (Proposal to change it to : Spring Cloud AWS Bedrock Starter )
[![Alpha](https://img.shields.io/badge/Release-Alpha-darkred)](https://img.shields.io/badge/Release-Alpha-darkred) ![Github Action CI Workflow Status](https://github.com/clue2solve/aws-bedrock-springtboot-starter/actions/workflows/ci.yml/badge.svg) [![Known Vulnerabilities](https://snyk.io/test/github/clue2solve/aws-bedrock-springtboot-starter/badge.svg?style=plastic)](https://snyk.io/test/github/clue2solve/aws-bedrock-springtboot-starter) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Bedrock 
This project provides a developer friendly way to access the services of AWS Bedrock, an AWS service that provides a managed Language Model (LLM) service with a catalog of supported LLMs.
This is a Spring Cloud Starter for the Bedrock service that removes all the boilerplate code required to access the service. It uses the standard Spring Cloud AWS Starter to provide the credentials and the AWS SDK, and the standards of Spring boot and Spring Cloud to provide the configuration and the auto-wiring of the service objects.

#### This Project Levereges Spring Cloud AWS for credential management. 

Just Provide the right properties as defined below and the apprpriate CredentialProvider object will be activated for injection into your Spring Objects. An Example for the creation of the BasicCredentials Provider is shown below. You can follow the link to understand all the other supported Credential models. 

```properties
# For BasicAwsCredentialsConfig
spring.cloud.aws.credentials.access-key=YOUR_ACCESS_KEY
spring.cloud.aws.credentials.secret-key=YOUR_SECRET_KEY
spring.cloud.aws.credentials.profile.name=YOUR_PROFILE
spring.cloud.aws.credentials.profile.path=~/.aws/credentials
```
See the [Credentials](https://docs.awspring.io/spring-cloud-aws/docs/3.0.3/reference/html/index.html#credentials) section of Spring Cloud AWS Starter documentation for alternate configuration options.


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
Once the right Credentials provider is injected, you can expect the activation of the appropriate versions of the BedrockClient and the BedrockRuntimeClient. These will be conditional on the `bedrock.client.type` property. the options there are `sync` and `async`, which drives the activation of the regular or the async versions of the client.s. Example:
```property
bedrock.client.type=sync
```

One can use the activated clients to interact with the AWS Bedrock service based on the docs for the Bedrock Service. 

### Bedrock Service
The best part of the Starter is the activation of the individual service objects which has the `invoke` methods.
All you need to do is provide the properties like one one of the sets below to activate the appropriate implementations of the BedrockService Interface ( Claude, Jurasic or Llama).

#### Claude Model Params
```properties
aws.bedrock.model.id=anthropic.claude-v2

aws.bedrock.model.claude.prompt=defaultPrompt
aws.bedrock.model.claude.temperature=0.5
aws.bedrock.model.claude.topP=0.5
aws.bedrock.model.claude.topK=50
aws.bedrock.model.claude.maxTokensToSample=100
```
#### Jurasic Model Params
```properties 
aws.bedrock.model.id=ai21.j2-mid-v1

aws.bedrock.model.jurassic.prompt=Your prompt here
aws.bedrock.model.jurassic.maxTokens=200
aws.bedrock.model.jurassic.temperature=0.5
```

#### Llama2 Model Params
```properties
aws.bedrock.model.id=anthropic.llama2-v1

aws.bedrock.model.llama2.prompt=Your prompt here
aws.bedrock.model.llama2.maxTokens=200
aws.bedrock.model.llama2.temperature=0.5

```

### Usage
Once activated the Service can be autowired and used as below.

```Java
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
```properties
# For BasicAwsCredentialsConfig
aws.credentials.provider=basic
aws.credentials.accessKey=YOUR_ACCESS_KEY
aws.credentials.secretKey=YOUR_SECRET_KEY

# For SessionTokenAwsCredentialsConfig
#aws.credentials.provider=session
#aws.credentials.accessKey=YOUR_ACCESS_KEY
#aws.credentials.secretKey=YOUR_SECRET_KEY
#aws.credentials.sessionToken=YOUR_SESSION_TOKEN

# For EnvironmentAwsCredentialsConfig
#aws.credentials.provider=environment
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

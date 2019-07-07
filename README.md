# Dropwizard Kotlin App

## About
This is the source code repository for the series of Dropwizard Kotlin App tutorials on [mannanlive.com](http://mannanlive.com).

## Chapters
### 1.0 Build Tools

#### 1.1 Maven

Source: http://mannanlive.com/2019/03/03/building-a-kotlin-dropwizard-maven-rest-api-from-scratch/

Related Files: [pom.xml](pom.xml)

### 2.0 Coding

#### 2.1 Service, Resource and Configuration

Source: http://mannanlive.com/2019/03/04/building-a-kotlin-dropwizard-maven-rest-api-from-scratch-part-2/

Related Files:
```text
|- src/main
  |- resources 
    |- app-config.yml
  |- kotlin/com/mannanlive/kotlindropwizardapp
    |- App.kt
    |- configuration
      |- AppConfig.kt
    |- resources
      |- EchoResource.kt
```

#### 2.2 Testing the Resource

Source: http://mannanlive.com/2019/03/07/building-a-kotlin-dropwizard-maven-rest-api-from-scratch-part-3/

Related Files:
```text
|- pom.xml ~
|- src/test
  |- resources/fixtures
    |- echo.json
  |- kotlin/com/mannanlive/kotlindropwizardapp/resources
    |- EchoResourceTest.kt
    |- EchoResourceIntegrationTest.kt
```

#### 2.3 Calling Web Services

Source: http://mannanlive.com/2019/04/02/dropwizard-kotlin-have-i-been-pwned-password-resource/

Related Files:
```text
|- pom.xml ~
|- src
  |- main
    |- resources
      |- app-config.yml ~
    |- kotlin/com/mannanlive/kotlindropwizardapp
      |- App.kt ~
      |- clients
        |- PwnedClient.kt
      |- configuration
        |- AppConfig.kt ~
      |- resources
        |- PwnedResource.kt
      |- services
        |- PwnedService.kt
  |- test/kotlin/com/mannanlive/kotlindropwizardapp
    |- resources
      |- PwnedResourceIntegration.kt
```

### 3.0 Running
#### 3.1 Command Line
Source: http://mannanlive.com/2019/03/16/building-a-kotlin-dropwizard-maven-rest-api-from-scratch-part-4/

Related Files: [pom.xml](pom.xml)~, [run.sh](run.sh)

#### 3.2 Docker
Source: http://mannanlive.com/2019/07/07/dropwizard-kotlin-in-a-docker-container/

Related Files: [Dockerfile](Dockerfile), [run.sh](run.sh)~
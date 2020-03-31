# rcalc-trail-svc 

A Java Starter Template driven by a happy union of Domain Driven Design, Hexagonal Architecture and gradle.

## Tech Stack :books:

* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html): :coffee: 
* [Gradle](https://gradle.org/): The build tool and about the only place where we got opinionated about technology. :runner: 
* [Junit 5](https://junit.org/junit5/): To enable TDD and some Ping Pong fun. :tennis:
* [AssertJ](https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html): Assertions, the human readable way. :eyes:
* [Mockito](https://site.mockito.org/): Fun mocks for subjects external to the system under test.
* [Docker](https://www.docker.com/): :shipit: 
* [Springboot](https://spring.io/projects/spring-boot): okay we lied, that's the other tech choice we made apart from gradle. :boot: 
* [Vavr](https://www.vavr.io/): when you want to go the functional way.
* [jeasy/easy-random](https://github.com/j-easy/easy-random): Selectively randomized testing.

### Gradle plugins used
* [Docker Compose Plugin](https://github.com/avast/gradle-docker-compose-plugin): we want gradle to flex it's muscles when we need them containers for testing or fun.
* [Checkstyle Plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html):  :cop: 

### Recommended IntelliJ IDEA Plugins (For Developers only)
* [Lombok](https://projectlombok.org/setup/intellij)
* [Docker](https://plugins.jetbrains.com/plugin/7724-docker/)
* [Markdown](https://plugins.jetbrains.com/plugin/7793-markdown/)
* [Bash](https://plugins.jetbrains.com/plugin/4230-bashsupport)


## Useful Commands :rocket:

### Build
```bash
./gradlew build
```
__NOTES__: This will also build the docker image for `springboot-adapter` locally.

To build the project inside a container locally.
```bash
docker run -v ${PWD}:/rcalc-trail-svc -w /rcalc-trail-svc openjdk:8-jdk-alpine ./gradlew assemble

docker-compose build  
```
__NOTE__: Containerized local build may be tad bit slower depending on the host machine capacity.

### Clean
```bash
./gradlew clean
```

### Test
```bash
./gradlew test
```
__NOTE__: The above command will only run the __unit__ tests. See below for integration test command.

To run __integration__ tests locally
```bash
./gradlew integrationTest
```
__NOTE__: The integration testing above might automatically spin up docker containers as required. These test containers are also automatically shutdown at the end of the test.
 
### run

* To bring up / run the whole app (springboot adapter): 
```bash
./gradlew run
```

* Alternatively, to bring up the whole app locally using docker only:
```bash
docker-compose up
```
__NOTE__: The above command will only work if the docker image was already built prior to running it, through either of the build commands.


### Stop
* To stop the running app (containers):
```bash
./gradlew stop
```

* Alternatively, to stop the whole app locally using docker only:
```bash
docker-compose down
```
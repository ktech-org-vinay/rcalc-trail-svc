# rcalc-trail-svc Documentation

## Project Structure

    config
        checkstyle
            checkstyle.xml              # <== Google Checkstyle configuration
    buildSrc                            # <== Build logic goes here
        src
            main
                groovy                  # <== Project gradle plugins
            test
                groovy                  # <== Build Logic tests
    core                                # <== Core Domain Module
        src
            main                        # <== Production code goes here
                java                    # <== Java sources and packages
                resources               # <== Configurations or assets
            test                        # <== Only unit tests go here
                java                    # <== Java Unit tests
                resources               # <== Java Unit test configurations or assets
        build.gradle                    # <== Module specific build script
    couchdb-adapter                     # <== Adapter module for couchdb 
        src
            integration                 # <== Integration tests
                java
                resources
            main
                java
                resources
            test                        # <== Unit tests only
                java
                resources
        build.gradle
    gradle                              # <== Gradle wrapper bootstrapped for consistent builds.
    springboot-adapter                  # <== Springboot adapter for REST API endpoints
        src
            integration
                java
                resources
            main
                java
                resources
            test
                java
                resources
        build.gradle
    kafka-adapter                       # <== Kafka adapter for messaging interface
        src
            integration
                java
                resources
            main
                java
                resources
            test
                java
                resources
        build.gradle
    src
        doc
            docs                        # <== Mkdocs site documents go here
                guide
                    installation.md     # <== Installation information
                    modules.md          # <== Modules guide
            mkdocs.yml                  # <== Mkdocs site configuration
    .gitignore                          
    build.gradle                        # <== Project wide build script
    docker-compose.yml                  
    gradlew                             # <== Gradle wrapper bootstrapped for consistent builds
    gradlew.bat                         # <== Gradle wrapper bootstrapped for consistent builds (Windows)
    README.md                           
    settings.gradle                     # <== Project wide gradle settings


## Tech Stack 

* [Java 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot): Latest Java LTS version at the moment(March 2020).
* [Gradle](https://gradle.org/): The build tool and about the only place where we got opinionated about technology. 
* [Junit 5](https://junit.org/junit5/): To enable TDD and some Ping Pong fun. :tennis:
* [AssertJ](https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html): Assertions, the human readable way. 
* [Mockito](https://site.mockito.org/): Fun mocks for subjects external to the system under test.
* [Docker](https://www.docker.com/): Containers abound.
* [Springboot](https://spring.io/projects/spring-boot): okay we lied, that's the other tech choice we made apart from gradle. 
* [Vavr](https://www.vavr.io/): when you want to go the functional way.
* [jeasy/easy-random](https://github.com/j-easy/easy-random): Selectively randomized testing.

### Gradle plugins used
* [Docker Compose Plugin](https://github.com/avast/gradle-docker-compose-plugin): we want gradle to flex it's muscles when we need them containers for testing or fun.
* [Checkstyle Plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html): To ensure we are following the rules.
* [Mkdocs Plugin](https://xvik.github.io/gradle-mkdocs-plugin/1.0.1/getting-started/): To generate local site documentation.

### Recommended IntelliJ IDEA Plugins (For Developers only)
* [Lombok](https://projectlombok.org/setup/intellij)
* [Docker](https://plugins.jetbrains.com/plugin/7724-docker/)
* [Markdown](https://plugins.jetbrains.com/plugin/7793-markdown/)
* [Bash](https://plugins.jetbrains.com/plugin/4230-bashsupport)

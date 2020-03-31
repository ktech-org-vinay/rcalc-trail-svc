# rcalc-trail-svc Developer Commands Manual

As a Java developer you are more likely to be using the direct native commands given below while locally.

### Build
```bash
./gradlew build
```

### Clean
```bash
./gradlew clean
```

### Code Checks
* To run code coverage reports:
```bash
./gradlew codeCoverage
```

* To run sonarqube uploader:
```bash
./gradlew sonarqube
```
__NOTE__: You will need to include the `systemProp.sonar.host.url` and `systemProp.sonar.login` settings to your `~/.gradle/gradle.settings` file pointing to the appropriate Sonarqube endpoint and tokens.

### Test
* To run unit tests:
```bash
./gradlew test
```

* To run __integration__ tests locally:
```bash
./gradlew integrationTest
```
__NOTE__: The integration testing above might automatically spin up docker containers as required. These test containers are also automatically shutdown at the end of the test.

### Docker
* To build the docker images:
```bash
./gradlew composeBuild
```
 
### Run

* To bring up / run the whole app (springboot adapter): 
```bash
./gradlew run
```

* To stop the running app (containers):
```bash
./gradlew stop
```

### Site Documents
* To generate the site documents
```bash
./gradlew mkdocsBuild
```

* To locally serve the site documents (for testing etc.)
```bash
./gradlew mkdocsServe
```


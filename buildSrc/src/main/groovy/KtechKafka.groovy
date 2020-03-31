import org.gradle.api.Plugin
import org.gradle.api.Project

class KtechKafka implements Plugin<Project> {
    @Override
    void apply(Project target) {
        applyDependencies(target)
        applyPlugins(target)
    }

    private applyDependencies(Project target) {
        target.dependencies {
            implementation target.project(':core')
            implementation 'org.springframework.boot:spring-boot-starter:2.2.4.RELEASE'
            implementation 'org.springframework.kafka:spring-kafka:2.4.2.RELEASE'
            testImplementation('org.springframework.boot:spring-boot-starter-test:2.2.4.RELEASE') {
                exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
            }
            implementation 'org.apache.avro:avro:1.9.2'
            implementation 'io.confluent:kafka-schema-registry-client:5.4.1'
            implementation('io.confluent:kafka-streams-avro-serde:5.4.1') {
                exclude group: 'org.slf4j', module: 'slf4j-api'
                exclude group: 'org.slf4j', module: 'slf4j-log4j12'
            }
            implementation('io.confluent:kafka-avro-serializer:5.4.1') {
                exclude group: 'org.slf4j', module: 'slf4j-api'
                exclude group: 'org.slf4j', module: 'slf4j-log4j12'
            }
        }
    }

    private applyPlugins(Project target) {
        target.getPluginManager().apply('com.commercehub.gradle.plugin.avro')
    }


}

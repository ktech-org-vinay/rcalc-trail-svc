import org.gradle.api.Plugin
import org.gradle.api.Project

class KtechSpringboot implements Plugin<Project> {
    @Override
    void apply(Project target) {
        applyPlugins(target)
        applyDependencies(target)
    }

    private applyDependencies(Project target) {
        target.dependencies {
            implementation target.project(':core')
            implementation target.project(':couchdb-adapter')
            implementation target.project(':kafka-adapter')
            implementation 'org.springframework.boot:spring-boot-starter-web'
            implementation 'org.springframework.boot:spring-boot-starter-webflux'
            implementation 'org.springframework.boot:spring-boot-starter-actuator'
            implementation 'io.micrometer:micrometer-registry-statsd'
            testImplementation('org.springframework.boot:spring-boot-starter-test') {
                exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
            }
            testImplementation 'io.projectreactor:reactor-test'
        }
    }

    private void applyPlugins(Project target) {
        target.getPluginManager().apply('org.springframework.boot')
        target.getPluginManager().apply('io.spring.dependency-management')
        target.getPluginManager().apply('com.gorylenko.gradle-git-properties')
    }
}

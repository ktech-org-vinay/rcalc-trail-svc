import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.testing.Test

class KtechJava implements Plugin<Project> {
    @Override
    void apply(Project target) {
        applyPlugins(target)
        setRepositories(target)
        applyJavaConfigurations(target)
        applyIntegrationTestSetup(target)
        applyUnitTestSetup(target)
        applyDependencies(target)
    }

    private void applyJavaConfigurations(Project target) {
        target.java.sourceCompatibility = JavaVersion.VERSION_1_8
    }

    private void applyUnitTestSetup(Project target) {
        target.test {
            useJUnitPlatform()
        }
    }

    private void applyIntegrationTestSetup(Project target) {
        applyIntegrationTestSources(target)
        applyIntegrationTestConfigurations(target)
        createIntegrationTestTasks(target)
    }

    private Task createIntegrationTestTasks(Project target) {
        target.task('integrationTest', type: Test) {
            testClassesDirs = target.sourceSets.integration.output.classesDirs
            classpath = target.sourceSets.integration.runtimeClasspath
        }

        target.integrationTest {
            useJUnitPlatform()
        }
    }

    private applyIntegrationTestConfigurations(Project target) {
        target.configurations {
            integrationImplementation.extendsFrom testImplementation
            integrationRuntime.extendsFrom testRuntime
        }
    }

    private void applyIntegrationTestSources(Project target) {
        target.sourceSets {
            integration {
                java {
                    srcDir target.projectDir.path + "/src/integration/java"
                    compileClasspath += main.output + test.output
                    runtimeClasspath += main.output + test.output
                }
                resources.srcDir target.projectDir.path + "/src/integration/resources"
            }
        }
    }

    private applyPlugins(Project target) {
        target.getPluginManager().apply('java')
    }

    private setRepositories(Project target) {
        target.repositories {
            mavenCentral()
            maven {
                url = 'https://packages.confluent.io/maven/'
            }
        }
    }

    private applyDependencies(Project target) {
        target.dependencies {
            testCompile 'org.assertj:assertj-core:3.14.0'
            testCompile 'org.jeasy:easy-random-core:4.1.0'
            testCompile 'org.jeasy:easy-random-randomizers:4.1.0'
            testCompile 'org.mockito:mockito-core:1.+'
            testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.0'
            testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.6.0'
            integrationImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.0'
            integrationRuntime 'org.junit.jupiter:junit-jupiter-engine:5.6.0'
            compileOnly 'org.projectlombok:lombok:1.18.10'
            annotationProcessor 'org.projectlombok:lombok:1.18.10'
        }
    }
}

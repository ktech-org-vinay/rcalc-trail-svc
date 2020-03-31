import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

class KtechJacoco implements Plugin<Project> {
    @Override
    void apply(Project target) {
        target.getPluginManager().apply('jacoco')
        target.jacoco.toolVersion = "0.8.5"
        target.task('codeCoverageReport', type: JacocoReport) {
            executionData target.tasks["test"]
            if (target.tasks.findByName('integrationTest')) {
                executionData
            }
            reports {
                xml.enabled true
                html.enabled true
            }
            sourceSets target.sourceSets.main
        }
    }
}

import org.gradle.api.Plugin
import org.gradle.api.Project

class KtechSonarQube implements Plugin<Project> {
    @Override
    void apply(Project target) {
        target.getPluginManager().apply('org.sonarqube')
    }
}

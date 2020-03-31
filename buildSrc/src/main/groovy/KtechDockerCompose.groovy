import org.gradle.api.Plugin
import org.gradle.api.Project

class KtechDockerCompose implements Plugin<Project> {
    @Override
    void apply(Project target) {
        target.getPluginManager().apply('docker-compose')
    }
}

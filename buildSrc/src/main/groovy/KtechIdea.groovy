import org.gradle.api.Plugin
import org.gradle.api.Project

class KtechIdea implements Plugin<Project> {
    @Override
    void apply(Project target) {
        target.getPluginManager().apply('idea')
    }
}

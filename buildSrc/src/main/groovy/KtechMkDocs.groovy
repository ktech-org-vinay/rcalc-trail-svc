import org.gradle.api.Plugin
import org.gradle.api.Project

class KtechMkDocs implements Plugin<Project> {
    @Override
    void apply(Project target) {
        applyPlugins(target)
        applyPythonConfigurations(target)
        applyMkDocsConfigurations(target)
    }

    private void applyMkDocsConfigurations(Project target) {
        target.mkdocs {
            sourcesDir = './'
        }
    }

    private void applyPythonConfigurations(Project target) {
        target.python {
            installVirtualenv = true
            envPath = '.gradle/python'
            pythonBinary = 'python3'
        }
    }

    private applyPlugins(Project target) {
        target.getPluginManager().apply('ru.vyarus.mkdocs')
    }
}

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * This plugin contains common configurations for the project.
 *
 * It configures the application module as well as setting configurations that
 * are common to all library modules.
 */
class MyConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.all {
            when (this) {
                is LibraryPlugin -> {
                    val extension = project.extensions.getByType(
                        LibraryExtension::class.java
                    )
                    extension.configureCommon()
                    extension.configureLibrary()
                }
                is AppPlugin -> {
                    val extension = project.extensions.getByType(
                        AppExtension::class.java)
                    extension.configureCommon()
                    extension.configureLibrary(project)
                }
            }
        }
        project.commonTasks()
    }
}

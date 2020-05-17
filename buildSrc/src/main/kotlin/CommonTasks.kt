import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.model.Kapt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Tasks common among all subprojects
 */
fun Project.commonTasks() {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = freeCompilerArgs.plus(
                listOf(
                    "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
                )
            )
        }
    }

    tasks.withType<Test> {
        configureTests()
    }

}
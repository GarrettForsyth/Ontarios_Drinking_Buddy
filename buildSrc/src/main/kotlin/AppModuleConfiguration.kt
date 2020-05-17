import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

/**
 * Configuration for the application module.
 */
fun AppExtension.configureLibrary(project: Project) {

    val propertyFileNames = listOf("secrets.properties", "odb.properties")
    val properties = mutableMapOf<String, Properties>()
    propertyFileNames.forEach {fileName ->
        val file = project.rootProject.file(fileName)
        val property = Properties()
        property.load(FileInputStream(file))
        val key = fileName.substringBefore(".")
        properties[key] = property
    }

    defaultConfig.apply {
        applicationId(Config.applicationId)
        versionCode(Config.versionCode)
        versionName(Config.versionName)

        buildConfigField(
            "String",
            "baseUrl",
            properties["secrets"]?.getProperty("baseUrl") as String
        )
        buildConfigField(
            "String",
            "databaseName",
            properties["odb"]?.getProperty("databaseName") as String
        )

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

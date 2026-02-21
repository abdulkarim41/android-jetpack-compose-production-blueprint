

import com.iamkarim.configureKotlinJvm
import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            pluginManager.apply("org.jetbrains.kotlin.jvm")
            //apply(plugin = "org.jetbrains.kotlin.jvm")

            configureKotlinJvm()

            dependencies {
                "testImplementation"(libs.findLibrary("kotlin.test").get())
            }
        }
    }
}


import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "iamkarim.android.library")
            apply(plugin = "iamkarim.android.hilt")

            dependencies {
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:design-system"))

                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("androidx.hilt.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("androidx.navigation3.runtime").get())
            }
        }
    }
}

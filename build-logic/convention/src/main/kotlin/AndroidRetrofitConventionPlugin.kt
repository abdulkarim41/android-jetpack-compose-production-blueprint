

import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add(
                    "implementation",
                    libs.findLibrary("retrofit.core").get()
                )
                add(
                    "implementation",
                    libs.findLibrary("retrofit.adapter.rx3").get()
                )
                add(
                    "implementation",
                    libs.findLibrary("retrofit.converter.gson").get()
                )
                add("implementation", libs.findLibrary("gson").get())
                add(
                    "implementation",
                    libs.findLibrary("okhHttp.core").get()
                )
                add(
                    "implementation",
                    libs.findLibrary("okhHttp.logging.interceptor").get()
                )
            }
        }
    }
}

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.abdulkarim.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradleApiPlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConvention") {
            id = "iamkarim.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationComposeConvention") {
            id = "iamkarim.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}





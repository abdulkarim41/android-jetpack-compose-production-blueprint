plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.domain"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model.entity)

    implementation(libs.kotlinx.coroutines.android)
}
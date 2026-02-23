plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.retrofit)
}

android {
    namespace = "com.abdulkarim.data"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)
}
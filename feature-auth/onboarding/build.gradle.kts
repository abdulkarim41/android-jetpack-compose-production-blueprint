plugins {
    alias(libs.plugins.iamkarim.android.feature)
    alias(libs.plugins.iamkarim.android.library.compose)
}

android {
    namespace = "com.abdulkarim.onboarding"
}

dependencies {
    implementation(projects.core.secureStorage)
}

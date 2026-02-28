plugins {
    alias(libs.plugins.iamkarim.android.feature)
    alias(libs.plugins.iamkarim.android.library.compose)
}

android {
    namespace = "com.abdulkarim.splash"
}

dependencies {
    implementation(projects.core.navigation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}
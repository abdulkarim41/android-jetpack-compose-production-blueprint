plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.library.compose)
}

android {
    namespace = "com.abdulkarim.ui"
}

dependencies {
    api(projects.core.designSystem)
}
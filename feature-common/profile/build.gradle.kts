plugins {
    alias(libs.plugins.iamkarim.android.feature)
    alias(libs.plugins.iamkarim.android.library.compose)
}

android {
    namespace = "com.abdulkarim.profile"
}

dependencies {
    implementation(libs.coil.compose)
}
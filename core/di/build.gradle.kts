plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.retrofit)
}

android {
    namespace = "com.abdulkarim.di"
}

dependencies {
    implementation(libs.log.timber)
}
plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.navigation"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
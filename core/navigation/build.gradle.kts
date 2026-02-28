plugins {
    alias(libs.plugins.iamkarim.android.library)
}

android {
    namespace = "com.abdulkarim.navigation"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
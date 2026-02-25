plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.securestorage"
}

dependencies {
    implementation(libs.androidx.security.crypto)
}
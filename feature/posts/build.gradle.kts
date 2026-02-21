plugins {
    alias(libs.plugins.iamkarim.android.feature)
    alias(libs.plugins.iamkarim.android.library.compose)
}

android {
    namespace = "com.abdulkarim.posts"
}
//
//dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//}
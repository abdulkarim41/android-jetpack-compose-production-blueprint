plugins {
    alias(libs.plugins.iamkarim.android.application)
    alias(libs.plugins.iamkarim.android.application.compose)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.abdulkarim.android_jetpack_compose"

    defaultConfig {
        applicationId = "com.abdulkarim.android_jetpack_compose"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    implementation(projects.featureCredential.splash)
    implementation(projects.featureCredential.onboarding)
    implementation(projects.featureCredential.login)

    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.designSystem)
    implementation(projects.core.ui)
    implementation(projects.core.di)

    implementation(projects.core.model.apiresponse)
    implementation(projects.core.model.entity)


    implementation(projects.feature.posts)


    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
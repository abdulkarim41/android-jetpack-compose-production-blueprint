plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.retrofit)
}

android {
    namespace = "com.abdulkarim.data"
}

dependencies {
    api(projects.core.di)
    api(projects.core.common)
    api(projects.core.domain)
    api(projects.core.datastore)
    api(projects.core.secureStorage)
    api(projects.core.model.entity)
    api(projects.core.model.apiresponse)

    implementation(libs.kotlinx.coroutines.android)
}
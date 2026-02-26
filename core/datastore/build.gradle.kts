plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.datastore"
}

dependencies {
    api(projects.core.model.entity)
    implementation(libs.androidx.datastore.preferences)
}
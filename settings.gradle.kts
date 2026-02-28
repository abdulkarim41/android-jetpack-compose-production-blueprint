pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-jetpack-compose-production-blueprint"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:common")
include(":core:design-system")
include(":core:ui")
include(":core:data")
include(":core:domain")
include(":core:di")
include(":core:secure-storage")
include(":core:datastore")
include(":core:navigation")
include(":core:model:apiresponse")
include(":core:model:entity")
include(":feature-auth:login")
include(":feature-auth:onboarding")
include(":feature-auth:splash")
include(":feature-common:profile")
include(":feature:product")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
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

rootProject.name = "Funch-AOS"
include(":app")

// core
include(":core:designsystem")
include(":core:testing")
include(":core:network")
include(":core:datastore")

// feature
include(":feature:profile")
include(":feature:home")
// include(":feature:match")

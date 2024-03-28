pluginManagement {
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
        maven {url = uri("https://repositories.tomtom.com/artifactory/maps-sdk-legacy-android" ) }
    }
}

rootProject.name = "MapExample"
include(":app")

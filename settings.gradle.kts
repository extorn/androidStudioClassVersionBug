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
        maven { url = java.net.URI("https://jitpack.io") }
    }
}
buildCache {
    local {
        directory = File(rootDir, "build-cache")
        removeUnusedEntriesAfterDays = 14
    }
}
rootProject.name = "MyApplication"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
// DO NOT add as it is a reserved and automatic inclusion essentially -  include(":buildSrc")

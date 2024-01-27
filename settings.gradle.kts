@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Sample Tested App"

include(":app")

include(":core:presentation")
include(":core:domain")
include(":core:data")

include(":testsetup:shared")

include(":feature:home:presentation")
include(":feature:home:domain")
include(":feature:home:data")

include(":feature:details:presentation")
include(":feature:details:domain")
include(":feature:details:data")

include(":feature:personalisation:presentation")
include(":feature:personalisation:domain")
include(":feature:personalisation:data")

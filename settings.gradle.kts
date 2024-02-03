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
    }
}

rootProject.name = "SoapShop"
include(":app")
include(":feature:registration")
include(":core:ui")
include(":core:components")
include(":core:util")
include(":core:data")
include(":core:domain")
include(":feature:catalog")
include(":feature:profile")
include(":feature:main")

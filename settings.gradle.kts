pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
//        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "AtFawry"
include(":androidApp")
include(":shared")
include(":buildsrc")

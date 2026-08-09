pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application")           version "8.13.2"
        id("com.android.library")               version "8.13.2"
        id("org.jetbrains.kotlin.multiplatform") version "2.1.0"
        id("org.jetbrains.compose")             version "1.7.1"
        id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
        id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
        id("com.google.dagger.hilt.android")    version "2.51.1"
        id("com.google.devtools.ksp")           version "2.1.0-1.0.29"
        id("androidx.room")                    version "2.7.0-alpha11"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://androidx.dev/storage/compose-test/repository") }
        maven { url = uri("https://artifacts.consensys.net/public/maven/maven/") }
    }
}

rootProject.name = "WeTheGoverned"
include(":app")
include(":shared")
include(":models")

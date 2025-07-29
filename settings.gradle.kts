rootProject.name = "KmpClothingStore"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.myket.ir")
//        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
//        maven("https://repo.ito.gov.ir/gradle/maven_central/")
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
//        maven("https://repo.ito.gov.ir/gradle/maven_central/")
        maven("https://maven.myket.ir")
//        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
//include(":wearclothingstore")

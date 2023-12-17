@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex.home.presentation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:presentation"))

    implementation(project(":feature:home:domain"))

    testImplementation(project(":testsetup:shared"))
}

@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex.personalisation.presentation"

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get() }
}

dependencies {
    implementation(project(":core:presentation"))

    implementation(project(":feature:personalisation:domain"))

    testImplementation(project(":testsetup:shared"))
}

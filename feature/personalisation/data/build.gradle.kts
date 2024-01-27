@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version("1.9.22")
}

android {
    namespace = "au.sgallitz.pokedex.personalisation.data"

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get() }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:presentation"))

    implementation(project(":feature:personalisation:domain"))

    implementation(platform(libs.compose.bom))
    implementation(libs.material)
    implementation(libs.compose.material3)
    implementation(libs.coil.compose)
}

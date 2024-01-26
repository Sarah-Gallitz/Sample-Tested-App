@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex.core.presentation"

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get() }
}

dependencies {
    api(project(":core:domain"))

    api(platform(libs.compose.bom))
    api(libs.compose.material3)
    api(libs.compose.uitooling.preview)
    
    api(libs.androidx.compose.activity)
    api(libs.androidx.compose.navigation)

    api(libs.koin.android)
    api(libs.koin.compose)

    debugApi(libs.compose.uitooling)
    debugApi(libs.compose.uitest.manifest)

    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
}

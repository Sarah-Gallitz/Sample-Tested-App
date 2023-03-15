@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex.core.presentation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform("androidx.compose:compose-bom"))

    api("androidx.activity:activity-compose")
    api("androidx.compose.material3:material3")
    api("androidx.navigation:navigation-compose")

    api("androidx.compose.ui:ui-tooling-preview")
    debugApi("androidx.compose.ui:ui-tooling")
    debugApi("androidx.compose.ui:ui-test-manifest")

    api("io.insert-koin:koin-android")
    api("io.insert-koin:koin-androidx-compose")

    implementation("io.coil-kt:coil-compose")
    implementation("io.coil-kt:coil-svg")
}

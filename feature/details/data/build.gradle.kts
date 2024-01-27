@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version("1.9.22")
}

android {
    namespace = "au.sgallitz.pokedex.details.data"
}

dependencies {
    implementation(project(":core:data"))

    implementation(project(":feature:details:domain"))

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
}

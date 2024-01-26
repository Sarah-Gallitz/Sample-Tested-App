@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex.details.data"
}

dependencies {
    implementation(project(":core:data"))

    implementation(project(":feature:details:domain"))
}

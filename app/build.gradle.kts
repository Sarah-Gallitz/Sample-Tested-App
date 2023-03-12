plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex"

    defaultConfig {
        applicationId = "au.sgallitz.pokedex"
    }
}

dependencies {
    implementation(project(":core:presentation"))

    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:home:domain"))

    implementation("io.insert-koin:koin-android")
}

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
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:data"))

    implementation("io.insert-koin:koin-android")
}

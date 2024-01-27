plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "au.sgallitz.pokedex"

    defaultConfig {
        applicationId = "au.sgallitz.pokedex"
    }

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get() }
}

dependencies {
    implementation(project(":core:presentation"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:data"))

    implementation(project(":feature:details:presentation"))
    implementation(project(":feature:details:domain"))
    implementation(project(":feature:details:data"))

    implementation(project(":feature:personalisation:presentation"))
    implementation(project(":feature:personalisation:domain"))
    implementation(project(":feature:personalisation:data"))

    implementation(libs.coil.compose)
}

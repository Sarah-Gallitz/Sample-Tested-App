plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
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
    implementation(libs.androidx.compose.activity)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
}

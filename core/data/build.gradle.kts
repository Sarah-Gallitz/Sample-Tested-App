@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3")
    id("org.jetbrains.kotlin.plugin.serialization") version("1.9.22")
}

android {
    namespace = "au.sgallitz.pokedex.core.data"
}

apollo {
    service("pokeApi") {
        packageName.set("au.sgallitz.pokedex.core.data.graphql")
        schemaFile.set(rootProject.file("graphql/schema.json"))
    }
}

dependencies {
    api(project(":core:domain"))

    implementation(libs.graphql.apollo3.runtime)
    implementation("com.apollographql.apollo3:apollo-normalized-cache:3.7.5")
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.5")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
}

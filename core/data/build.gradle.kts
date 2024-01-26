@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3")
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
}

@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3")
}

android {
    namespace = "au.sgallitz.pokedex.home.data"
}

apollo {
    service("pokeApi") {
        packageName.set("au.sgallitz.pokedex.home.data.graphql")
        schemaFile.set(rootProject.file("graphql/schema.json"))
    }
}

dependencies {
    implementation(project(":core:data"))

    implementation(project(":feature:home:domain"))

    implementation("com.apollographql.apollo3:apollo-runtime")
}

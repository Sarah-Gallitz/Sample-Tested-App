plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":core:domain"))

    implementation(project(":feature:personalisation:domain"))
}

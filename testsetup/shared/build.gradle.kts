plugins {
    id("kotlin")
}

dependencies {
    api(libs.junit)
    api(kotlin("test"))

    api(libs.mockk)

    api(libs.kotlinx.coroutines.test)
}

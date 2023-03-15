plugins {
    id("kotlin")
}

dependencies {
    api("junit:junit")
    api(kotlin("test"))

    api("io.mockk:mockk")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-test")
}

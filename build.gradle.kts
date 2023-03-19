@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jmailen.kotlinter") version "3.13.0" apply false
    id("com.apollographql.apollo3") version("3.7.5") apply false
}

allprojects {
    plugins.apply("org.jmailen.kotlinter")

    afterEvaluate {
        extensions.findByType(BaseExtension::class)?.apply {
            defaultConfig {
                minSdk = 21
                targetSdk = 33
                compileSdkVersion(33)

                versionCode = 1
                versionName = "1.0"


                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.4.2"
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }
        }

        dependencies {
            val dependencyConstraints = listOf(
                "androidx.compose:compose-bom:2023.01.00",

                "androidx.activity:activity-compose:1.6.1",
                "androidx.navigation:navigation-compose:2.5.3",

                "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4",
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4",

                "io.insert-koin:koin-android:3.2.1",
                "io.insert-koin:koin-androidx-compose:3.2.1",
                "io.insert-koin:koin-core:3.2.1",

                "io.coil-kt:coil-compose:2.2.2",
                "io.coil-kt:coil-svg:2.2.2",

                "com.apollographql.apollo3:apollo-runtime:3.7.5",

                "junit:junit:4.13.2",
                "io.mockk:mockk:1.13.4"
            )

            configurations.forEach { configuration ->
                dependencyConstraints.forEach { dependency ->
                    constraints.add(configuration.name, dependency)
                }
            }
        }
    }
}

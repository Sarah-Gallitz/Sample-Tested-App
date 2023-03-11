@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

allprojects {
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
                "androidx.activity:activity-compose:1.6.1"
            )

            configurations.forEach { configuration ->
                dependencyConstraints.forEach { dependency ->
                    constraints.add(configuration.name, dependency)
                }
            }
        }
    }
}

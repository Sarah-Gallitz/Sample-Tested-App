@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kt.lint) apply false
    alias(libs.plugins.graphql.apollo3) apply false
}

allprojects {
    plugins.apply("org.jmailen.kotlinter")

    afterEvaluate {
        extensions.findByType(BaseExtension::class)?.apply {
            defaultConfig {
                minSdk = 21
                targetSdk = 34
                compileSdkVersion(34)

                versionCode = 1
                versionName = "1.0"


                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }

                vectorDrawables.useSupportLibrary = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }
        }
    }
}

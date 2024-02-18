@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kt.lint) apply false
    alias(libs.plugins.graphql.apollo3) apply false
}

allprojects {
    plugins.apply(rootProject.libs.plugins.kt.lint.get().pluginId)

    afterEvaluate {
        extensions.findByType(BaseExtension::class)?.apply {
            val appVersionCode = 1
            val appVersionName = "1.0.0-LOCAL"

            defaultConfig {
                minSdk = 21
                targetSdk = 34
                compileSdkVersion(34)

                versionCode = appVersionCode
                versionName = appVersionName


                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }
}

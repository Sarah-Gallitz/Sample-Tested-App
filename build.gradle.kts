plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}


allprojects {
    afterEvaluate {
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

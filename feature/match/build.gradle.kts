plugins {
    alias(libs.plugins.funch.feature)
    alias(libs.plugins.funch.compose)
}

android {
    namespace = "com.moya.funch.match"

    packaging {
        resources.excludes.add("META-INF/LICENSE*")
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.testing)

    implementation(libs.compose.lottie)
}

plugins {
    alias(libs.plugins.ktlint)
    alias(libs.plugins.funch.feature)
    alias(libs.plugins.funch.compose)
}

android {
    namespace = "com.moya.funch.home"
}

dependencies {
    implementation(projects.core.designsystem)
}
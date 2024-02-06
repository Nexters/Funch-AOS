plugins {
    alias(libs.plugins.funch.feature)
    alias(libs.plugins.funch.compose)
}

android {
    namespace = "com.moya.funch.match"
}

dependencies {
    implementation(projects.core.designsystem)
}

plugins {
    alias(libs.plugins.funch.feature)
    alias(libs.plugins.funch.compose)
}

android {
    namespace = "com.moya.funch.profile"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
}

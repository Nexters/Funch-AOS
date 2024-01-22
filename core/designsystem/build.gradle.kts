plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.funch.kotlin)
    alias(libs.plugins.funch.compose)
}

android {
    namespace = "com.moya.funch.designsystem"
}

dependencies {
    implementation(libs.timber)
}


@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.funch.feature)
    alias(libs.plugins.funch.compose)
}

android {
    namespace = "com.moya.funch.designsystem"
}


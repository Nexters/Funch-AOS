plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.funch.android.kotlin)
    alias(libs.plugins.funch.hilt)
    alias(libs.plugins.funch.kotlinx.serialization)
}

android {
    namespace = "com.moja.funch.network"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
}

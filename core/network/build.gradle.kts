plugins {
    alias(libs.plugins.funch.android.library)
}

android {
    namespace = "com.moja.funch.network"
}

dependencies {
    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
}

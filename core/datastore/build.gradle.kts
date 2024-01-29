plugins {
    // alias(libs.plugins.ktlint)
    alias(libs.plugins.funch.android.library)
}

android {
    namespace = "com.moja.funch.datastore"
}

dependencies {
    implementation(libs.security)
}

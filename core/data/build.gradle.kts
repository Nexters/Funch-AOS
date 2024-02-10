plugins {
    alias(libs.plugins.funch.android.library)
    alias(libs.plugins.funch.junit5)
}

android {
    namespace = "com.moja.funch.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.datastore)
    implementation(projects.core.testing)
    implementation(projects.core.domain)

    // test
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
}

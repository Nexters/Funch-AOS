plugins {
    alias(libs.plugins.funch.android.library)
    alias(libs.plugins.funch.junit5)
}

android {
    namespace = "com.moja.funch.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.testing)
    // implementation(projects.core.domain) TODO: domain 모듈이 만들어지면 추가

    // test
    testImplementation(libs.kotlin.coroutines.test)
}

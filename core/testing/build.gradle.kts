plugins {
    `java-library`
    alias(libs.plugins.funch.jvm.library)
}

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.bundles.junit5)
    implementation(libs.kotlin.coroutines.test)
}

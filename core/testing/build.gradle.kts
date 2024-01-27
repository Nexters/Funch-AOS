plugins {
    `java-library`
    kotlin("jvm")
}

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.bundles.junit5)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.test)
}

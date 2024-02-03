plugins {
    `java-library`
    alias(libs.plugins.funch.jvm.library)
    alias(libs.plugins.ktlint)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.javax.inject)
    // test
    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.truth)
    testImplementation(libs.kotlin.coroutines.test)
}

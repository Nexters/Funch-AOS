import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.ktlint)
    alias(libs.plugins.funch.android.library)
    alias(libs.plugins.funch.kotlinx.serialization)
    alias(libs.plugins.funch.junit5)
}

val properties =
    Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }
android {
    namespace = "com.moja.funch.network"

    buildTypes {
        // getByName("release") {
        //     buildConfigField(
        //         "String", "FUNCH_BASE_URL", properties.getProperty("FUNCH_BASE_URL")
        //     )
        // }
        getByName("debug") {
            buildConfigField(
                "String",
                "FUNCH_DEBUG_BASE_URL",
                properties.getProperty("FUNCH_DEBUG_BASE_URL"),
            )
        }
    }
}

dependencies {
    implementation(projects.core.datastore)
    implementation(projects.core.testing)

    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
    // test
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk.webserver)
}


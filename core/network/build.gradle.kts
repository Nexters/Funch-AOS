import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.ktlint)
    alias(libs.plugins.funch.android.library)
}

val properties = Properties().apply {
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
                "String", "FUNCH_DEBUG_BASE_URL", properties.getProperty("FUNCH_DEBUG_BASE_URL")
            )
        }
    }
}

dependencies {
    implementation(projects.core.datastore)

    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
}

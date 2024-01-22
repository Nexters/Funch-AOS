plugins {
    alias(libs.plugins.funch.application)
    alias(libs.plugins.funch.compose)
    alias(libs.plugins.ktlint)
//    alias(libs.plugins.google.services)
//    alias(libs.plugins.app.distribution)
//    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.moya.funch"

    defaultConfig {
        applicationId = "com.moya.funch"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    // feature
//    implementation(projects.feature.profile)
//    implementation(projects.feature.match)

//    implementation(libs.coil.core)
    implementation(libs.startup)
//    implementation(libs.security)
    implementation(libs.splash.screen)

    // Google
//    implementation(libs.google.android.gms)

    // Third Party
    implementation(libs.coil.core)
//    implementation(libs.kakao.login)
    implementation(libs.bundles.retrofit)
    implementation(libs.lifecycle)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3.compose)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Firebase
//    implementation(platform(libs.firebase))
//    implementation(libs.bundles.firebase)
}

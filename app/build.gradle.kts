import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.funch.application)
    alias(libs.plugins.funch.compose)
//    alias(libs.plugins.google.services)
//    alias(libs.plugins.app.distribution)
//    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.moya.funch"

    packaging {
        resources.excludes.add("META-INF/LICENSE*")
    }

    defaultConfig {
        applicationId = "com.moya.funch"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
    }

    signingConfigs {
        create("release") {
            Properties().apply {
                load(FileInputStream(rootProject.file("local.properties")))
                storeFile = rootProject.file(this["STORE_FILE"] as String)
                keyAlias = this["KEY_ALIAS"] as String
                keyPassword = this["KEY_PASSWORD"] as String
                storePassword = this["STORE_PASSWORD"] as String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    // core
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    // feature
    implementation(projects.feature.profile)
    implementation(projects.feature.home)
    implementation(projects.feature.match)
    implementation(projects.feature.onboarding)
//    implementation(libs.coil.core)
    implementation(libs.startup)
//    implementation(libs.security)
    implementation(libs.splash.screen)

    // Google
//    implementation(libs.google.android.gms)

    // Third Party
    implementation(libs.compose.lottie)
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

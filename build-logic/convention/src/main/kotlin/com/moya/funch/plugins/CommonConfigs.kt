package com.moya.funch.plugins

import com.android.build.gradle.BaseExtension
import com.moya.funch.plugins.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

internal fun Project.configureAndroidCommonPlugin() {
    val properties =
        Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

    apply<AndroidKotlinPlugin>()
    apply<KotlinSerializationPlugin>()
    with(plugins) {
        apply("kotlin-kapt")
        apply("kotlin-parcelize")
    }
    apply<AndroidHiltPlugin>()

    extensions.getByType<BaseExtension>().apply {
        defaultConfig {
//            val kakaoApiKey = properties["kakaoApiKey"] as? String ?: ""
//            val punchBaseUrl = properties["punchBaseUrl"] as? String ?: ""
//
//            manifestPlaceholders["kakaoApiKey"] = properties["kakaoApiKey"] as String
//            manifestPlaceholders["punchBaseUrl"] = properties["punchBaseUrl"] as String
//
//            buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
//            buildConfigField("String", "BASE_URL", "\"${punchBaseUrl}\"")
        }
        buildFeatures.apply {
            buildConfig = true
        }
    }

    dependencies {
        add("implementation", libs.findLibrary("core.ktx").get())
        add("implementation", libs.findLibrary("appcompat").get())
        add("implementation", libs.findBundle("lifecycle").get())
        add("implementation", libs.findLibrary("material").get())
        add("implementation", libs.findLibrary("timber").get())
    }
}

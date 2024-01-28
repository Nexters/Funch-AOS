package com.moya.funch.plugins

import com.android.build.gradle.LibraryExtension
import com.moya.funch.plugins.utils.configureAndroidCommonPlugin
import com.moya.funch.plugins.utils.configureKotlinAndroid
import com.moya.funch.plugins.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) =
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
            }
            configureAndroidCommonPlugin()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
            }

            dependencies {
                add("testImplementation", kotlin("test"))
            }
        }
}

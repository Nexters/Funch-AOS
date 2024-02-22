package com.moya.funch.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.moya.funch.plugins.utils.configureAndroidCommonPlugin
import com.moya.funch.plugins.utils.configureKotlinAndroid
import com.moya.funch.plugins.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
            }
            configureAndroidCommonPlugin()

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
            }

            dependencies {
                add("implementation", libs.findLibrary("core.ktx").get())
                add("implementation", libs.findLibrary("appcompat").get())
                add("implementation", libs.findBundle("lifecycle").get())
                add("implementation", libs.findLibrary("material").get())
                add("testImplementation", kotlin("test"))
            }
        }
    }
}

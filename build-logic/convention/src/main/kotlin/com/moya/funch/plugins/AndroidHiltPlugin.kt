package com.moya.funch.plugins

import com.moya.funch.plugins.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) =
        with(target) {
            with(plugins) {
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt").get())
                add("kapt", libs.findLibrary("hilt.kapt").get())
                add("testImplementation", libs.findLibrary("hilt.testing").get())
                add("kaptTest", libs.findLibrary("hilt.testing.compiler").get())
            }
        }
}

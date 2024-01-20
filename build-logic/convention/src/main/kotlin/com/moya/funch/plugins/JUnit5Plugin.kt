package com.moya.funch.plugins

import com.moya.funch.plugins.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JUnit5Plugin : Plugin<Project> {
    override fun apply(target: Project): Unit =
        with(target) {
            with(plugins) {
                apply("de.mannodermaus.android-junit5")
            }

            dependencies {
                add("testImplementation", libs.findBundle("junit5.test").get())
                add("androidTestImplementation", libs.findLibrary("junit5").get())
                add("androidTestImplementation", libs.findLibrary("junit5.params").get())
                add("androidTestImplementation", libs.findLibrary("junit5.android.test.core").get())
                add("androidTestRuntimeOnly", libs.findLibrary("junit5.android.test.runner").get())
            }
        }
}

package com.moya.funch.plugins

import com.moya.funch.plugins.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JUnit5Plugin : Plugin<Project> {
    override fun apply(target: Project): Unit =
        with(target) {
            dependencies {
                add("testImplementation", libs.findBundle("junit5").get())
                add("testImplementation", libs.findLibrary("truth").get())
            }
        }
}

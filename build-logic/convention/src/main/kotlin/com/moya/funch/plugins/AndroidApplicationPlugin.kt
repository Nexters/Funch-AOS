package com.moya.funch.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) =
        with(target) {
            plugins.apply("com.android.application")
            configureAndroidCommonPlugin()
        }
}

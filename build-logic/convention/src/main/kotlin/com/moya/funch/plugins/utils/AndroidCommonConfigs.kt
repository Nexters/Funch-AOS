package com.moya.funch.plugins.utils

import com.android.build.gradle.BaseExtension
import com.moya.funch.plugins.AndroidHiltPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCommonPlugin() {
    apply<AndroidHiltPlugin>()

    extensions.getByType<BaseExtension>().apply {
        buildFeatures.apply {
            buildConfig = true
        }
    }

    dependencies {
        add("implementation", libs.findLibrary("timber").get())
    }
}

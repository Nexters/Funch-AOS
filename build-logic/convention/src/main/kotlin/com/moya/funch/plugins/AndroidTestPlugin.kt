package com.moya.funch.plugins

import com.android.build.gradle.BaseExtension
import com.moya.funch.plugins.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidTestPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit =
        with(target) {
            apply<JUnit5Plugin>()
            apply("de.mannodermaus.android-junit5")

            extensions.getByType<BaseExtension>().apply {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    testInstrumentationRunnerArguments["runnerBuilder"] =
                        "de.mannodermaus.junit5.AndroidJUnit5Builder"
                }

                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }

                packagingOptions {
                    resources.excludes.add("META-INF/LICENSE*")
                }
            }

            dependencies {
                add("testImplementation", libs.findLibrary("junit").get())
                add("debugImplementation", libs.findLibrary("truth").get())
                add("testImplementation", libs.findLibrary("robolectric").get())
                add("androidTestRuntimeOnly", libs.findLibrary("junit5-engine").get())
                add("androidTestImplementation", libs.findLibrary("junit5-android-test-core").get())
                add("androidTestRuntimeOnly", libs.findLibrary("junit5-android-test-runner").get())
                add("androidTestImplementation", libs.findBundle("androidx.android.test").get())
            }
        }
}

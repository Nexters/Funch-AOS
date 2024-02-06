import org.jlleitschuh.gradle.ktlint.KtlintExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.kotlin.gradleplugin)
        classpath(libs.hilt.plugin)
        classpath(libs.agp)
        classpath(libs.ktlint)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.junit5) apply false
    alias(libs.plugins.ktlint) apply false
//    alias(libs.plugins.google.services) apply false
//    alias(libs.plugins.app.distribution) apply false
//    alias(libs.plugins.crashlytics) apply false
}
//
// subprojects {
//     apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
//
//     configure<KtlintExtension> {
//         filter {
//             exclude { element -> element.file.path.contains("generated/") }
//         }
//         version.set("0.49.1")
//         android.set(true)
//         coloredOutput.set(true)
//         verbose.set(true)
//         outputToConsole.set(true)
//         additionalEditorconfig.set(
//             mapOf(
//                 "kotlin" to """
//             max_line_length=120
//             insert_final_newline=true
//             """.trimIndent()
//             ),
//             mapOf(
//                 "charset" to "utf-8",
//                 "end_of_line" to "lf",
//                 "indent_style" to "space",
//                 "indent_size" to 4,
//                 "max_line_length" to 180,
//                 "trim_trailing_whitespace" to true,
//                 "ktlint_standard_filename" to "disabled",
//                 "ktlint_standard_function_start_of_body_spacing" to "disabled",
//             )
//         )
//
//         reporters {
//             reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
//             reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
//         }
//     }
// }
//
// tasks.register("clean", Delete::class) {
//     delete(rootProject.buildDir)
// }

plugins {
    `kotlin-dsl`
}

group = "com.moya.funch.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradleplugin)
}

gradlePlugin {
    plugins {
        create("android-application") {
            id = "com.moya.funch.application" // id는 다른 모듈에서 실제 사용될 때 사용된다
            implementationClass =
                "com.moya.funch.plugins.AndroidApplicationPlugin"
        }
        create("android-feature") {
            id = "com.moya.funch.feature"
            implementationClass = "com.moya.funch.plugins.AndroidFeaturePlugin"
        }
        create("android-kotlin") {
            id = "com.moya.funch.android.kotlin"
            implementationClass = "com.moya.funch.plugins.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "com.moya.funch.hilt"
            implementationClass = "com.moya.funch.plugins.AndroidHiltPlugin"
        }
        create("kotlin-serialization") {
            id = "com.moya.funch.serialization"
            implementationClass =
                "com.moya.funch.plugins.KotlinSerializationPlugin"
        }
        create("junit5") {
            id = "com.moya.funch.junit5"
            implementationClass = "com.moya.funch.plugins.JUnit5Plugin"
        }
        create("android-test") {
            id = "com.moya.funch.android.test"
            implementationClass = "com.moya.funch.plugins.AndroidTestPlugin"
        }
        create("compose") {
            id = "com.moya.funch.compose"
            implementationClass = "com.moya.funch.plugins.ComposePlugin"
        }
    }
}

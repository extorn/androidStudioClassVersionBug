import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.delit.buildsrc.GenerateNavArgsProguardRulesTask

plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.ksp)
}
tasks {
    named("preBuild").dependsOn(register("generateNavArgsProguardRules", GenerateNavArgsProguardRulesTask::class))
}

android {
    namespace = "com.delit.myapplication"

    defaultConfig {
        applicationId = "com.delit.myapplication"
        compileSdk = libs.versions.compile.sdk.version.get().toInt()
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_20.toString()
    }
    buildFeatures {
        compose = false
    }
}
kotlin {
    jvmToolchain(20)
}
dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
}
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "dev.eSolovei.eXpresso.sample.sharedTests"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildToolsVersion = "31.0.0"
}

kotlin {
    android()

    val iosArmSimulatorArm64 = iosSimulatorArm64()
    val iosArm64 = iosArm64()
    val iosX64 = iosX64()
    configure(listOf(
        iosArmSimulatorArm64,
        iosArm64,
        iosX64
    )) {
        binaries.framework {
            baseName = "sharedTests"
            embedBitcode("disable")

            transitiveExport = true
            export(project(":eXpresso"))
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
                api(project(":eXpresso"))
            }
        }

        val androidMain by getting{
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val iosSimulatorArm64Main by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            listOf(
                iosSimulatorArm64Main,
                iosX64Main,
                iosArm64Main
            ).forEach {
                it.dependsOn(this)
            }
            dependencies {
                api(project(":eXpresso"))
            }
        }

    }
    tasks.register<FatFrameworkTask>("fatDebugFramework") {
        baseName = "sharedTests"
        group = "build"
        // The default destination directory is "<build directory>/fat-framework".
//    destinationDir = buildDir.resolve("fat-framework/debug")
        from(
            iosArmSimulatorArm64.binaries.getFramework("DEBUG"),
            iosX64.binaries.getFramework("DEBUG")
        )
    }
}


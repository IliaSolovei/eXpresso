
//apply(from = rootProject.file("gradle/publish.gradle"))
plugins {
    kotlin("multiplatform")
//    `maven-publish`
    id("com.android.library")
}

android {
    namespace = "dev.eSolovei.eXpresso.android"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
//        versionCode = 1
//        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildToolsVersion = "31.0.0"

    sourceSets {
        getByName("main") {
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
        }
    }
}

kotlin {
    listOf(
        iosSimulatorArm64(),
        iosArm64(),
        iosX64()
    ).forEach {
        it.binaries {
            framework {
                baseName = "kuiks"
                embedBitcode("disable")
            }
        }
        it.compilations.getByName("main") {
            cinterops {
                val xctest by creating {
//                        // xctest_*.def require Xcode or symlink to be at /Applications/Xcode.app
//                        defFile = project(file("src/iosMain/xctest_${targetArchitecture}.def"))
//                    }
            }
        }
    }
        android()
//        fromPreset(presets.android, "android") {
//            publishLibraryVariants("release", "debug")
//        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

                implementation("androidx.test.ext:junit:1.1.3")
                implementation("androidx.test.espresso:espresso-core:3.4.0")
                implementation("androidx.test.espresso:espresso-contrib:3.4.0")
            }
        }

        val iosSimulatorArm64Main by getting
        val iosArm64Main by getting
        val iosX64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            listOf(
                iosSimulatorArm64Main,
                iosArm64Main,
                iosX64Main
            ).forEach {
                it.dependsOn(this)
            }
            dependencies {
//                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.6.4")
            }
        }
    }
}
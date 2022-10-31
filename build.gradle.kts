// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version = "1.7.20"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

//plugins {
//    id("pl.allegro.tech.build.axion-release") version "1.12.0"
//}

//scmVersion {
//    tag {
//        prefix = ""
//    }
//}

allprojects {

    group = "dev.eSolovei.eXpresso"
//    version = scmVersion.version

    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}


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

allprojects {

    group = "dev.eSolovei.eXpresso"

    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


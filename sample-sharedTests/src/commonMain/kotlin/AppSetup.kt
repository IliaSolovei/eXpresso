package dev.eSolovei.eXpresso.sample.sharedTests

import dev.eSolovei.eXpresso.Platform
import dev.eSolovei.eXpresso.platform

object AppSetup {
    val identifier: String
        get() = if (platform == Platform.iOS) "dev.michallaskowski.kuiks.SampleiOS" else "MainActivity"
}
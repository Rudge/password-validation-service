package com.rudge.tech.password.config

import io.javalin.Javalin
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

object AppConfig : KoinComponent {
    fun setup(): Javalin {
        startKoin {
            modules()
            fileProperties()
        }
        return Javalin.create { config ->
            config.contextPath = getKoin().getProperty("server_context") ?: ""
            config.enableWebjars()
        }.events { event ->
            event.serverStopping {
                stopKoin()
            }
        }.post("/validate", {})
                .apply { this.server()?.serverPort = getKoin().getProperty("server_port") ?: 7000 }
    }
}
package com.rudge.tech.password.config

import com.rudge.tech.password.controllers.PasswordValidationController
import com.rudge.tech.password.service.PasswordValidationService
import com.rudge.tech.password.service.validations.*
import io.javalin.Javalin
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.dsl.module

object AppConfig : KoinComponent {
    private val passwordValidationController by inject<PasswordValidationController>()
    
    fun setup(): Javalin {
        startKoin {
            modules(appModule)
            fileProperties()
        }
        return Javalin.create { config ->
            config.contextPath = getKoin().getProperty("server_context") ?: ""
            config.enableWebjars()
        }.events { event ->
            event.serverStopping {
                stopKoin()
            }
        }.post("/validate", passwordValidationController::validate)
                .apply { this.server()?.serverPort = getKoin().getProperty("server_port") ?: 7000 }
    }
}

val appModule = module {
    single {
        PasswordValidationService(
                listOf(
                        SizeNineCharsOrMorePasswordRule(),
                        AtLeastOneNumberPasswordRule(),
                        AtLeastOneLowerCasePasswordRule(),
                        AtLeastOneCapitalLetterPasswordRule(),
                        AtLeastOneSpecialCharPasswordRule()
                ))
    }
    single { PasswordValidationController(get()) }
}
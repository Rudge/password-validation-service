package com.rudge.tech.password.config

import com.rudge.tech.password.controllers.PasswordValidationController
import com.rudge.tech.password.service.PasswordValidationService
import com.rudge.tech.password.service.validations.*
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.post
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
        }.routes { routes() }
                .apply { this.server()?.serverPort = getKoin().getProperty("server_port") ?: 7000 }
    }

    private fun routes() {
        post("/validate") { ctx ->
            ctx.takeIf {
                ctx.header("Accept") == "application/vnd.password_details+json"
            }?.apply { passwordValidationController.validateWithErrorResponse(ctx) }
                    ?: passwordValidationController.validate(ctx)
        }
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
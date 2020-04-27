package com.rudge.tech.password.controllers

import com.rudge.tech.password.dto.ErrorResponse
import com.rudge.tech.password.dto.ValidatePasswordRequest
import com.rudge.tech.password.dto.ValidatePasswordResponse
import com.rudge.tech.password.service.PasswordValidationService
import io.javalin.http.Context

class PasswordValidationController(private val service: PasswordValidationService) {

    fun validate(ctx: Context) {
        ctx.takeIf {
            it.header("Content-Type")?.contains("application/vnd.password_details+json") ?: false
        }?.apply {
            service.validateReturnMessages(ctx.body<ValidatePasswordRequest>().value)
                    ?.takeIf { it.isNotEmpty() }
                    ?.apply {
                        ctx.status(400).json(ErrorResponse(this))
                    } ?: ctx.status(200).json(ValidatePasswordResponse(true))

        } ?: ctx.status(200)
                .json(ValidatePasswordResponse(
                        service.validate(ctx.body<ValidatePasswordRequest>().value)
                ))

    }
}
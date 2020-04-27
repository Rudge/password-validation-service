package com.rudge.tech.password.controllers

import com.rudge.tech.password.dto.ErrorResponse
import com.rudge.tech.password.dto.ValidatePasswordRequest
import com.rudge.tech.password.dto.ValidatePasswordResponse
import com.rudge.tech.password.service.PasswordValidationService
import io.javalin.http.Context

fun Context.isContentType(value: String) = this.header("Content-Type")?.contains(value) ?: false

class PasswordValidationController(private val service: PasswordValidationService) {

    fun validate(ctx: Context) {
        ctx.takeIf {
            it.isContentType("application/vnd.password_details+json")
        }?.apply {
            service.validateReturnMessages(ctx.body<ValidatePasswordRequest>().value)
                    .takeIf { it.isNotEmpty() }
                    ?.apply {
                        ctx.status(400).json(ErrorResponse(this))
                    } ?: ctx.status(200).json(ValidatePasswordResponse(true))

        } ?: ctx.status(200)
                .json(ValidatePasswordResponse(
                        service.validate(ctx.body<ValidatePasswordRequest>().value)
                ))

    }
}
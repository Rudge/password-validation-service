package com.rudge.tech.password.controllers

import com.rudge.tech.password.dto.ErrorResponse
import com.rudge.tech.password.dto.ValidatePasswordRequest
import com.rudge.tech.password.dto.ValidatePasswordResponse
import com.rudge.tech.password.service.PasswordValidationService
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PasswordValidationControllerTest {

    @Test
    fun `given one request to validate password without returning error details should call validate service`() {
        val serviceMock = mockk<PasswordValidationService>()
        val ctxMock = mockk<Context>()

        every { ctxMock.body<ValidatePasswordRequest>() } returns ValidatePasswordRequest("12312")
        every { ctxMock.status(200) } returns ctxMock
        every { ctxMock.json(ValidatePasswordResponse(true)) } returns ctxMock
        every { serviceMock.validate(any()) } returns true

        PasswordValidationController(serviceMock).validate(ctxMock)

        verify { ctxMock.status(200) }
        verify { ctxMock.json(ValidatePasswordResponse(true)) }
        verify { serviceMock.validate(any()) }
    }

    @Test
    fun `given one invalid password request to validate password with returning details should call validate service and return error list`() {
        val serviceMock = mockk<PasswordValidationService>()
        val ctxMock = mockk<Context>()
        val errors = listOf("error 1", "error 2")

        every { ctxMock.body<ValidatePasswordRequest>() } returns ValidatePasswordRequest("12312")
        every { ctxMock.status(400) } returns ctxMock
        every { ctxMock.json(ErrorResponse(errors)) } returns ctxMock
        every { serviceMock.validateReturnMessages(any()) } returns errors

        PasswordValidationController(serviceMock).validateWithErrorResponse(ctxMock)

        verify { ctxMock.status(400) }
        verify { ctxMock.json(ErrorResponse(errors)) }
        verify { serviceMock.validateReturnMessages(any()) }
    }

    @Test
    fun `given one valid password request to validate password with returning details should call validate service and not return error list`() {
        val serviceMock = mockk<PasswordValidationService>()
        val ctxMock = mockk<Context>()
        val errors = emptyList<String>()

        every { ctxMock.body<ValidatePasswordRequest>() } returns ValidatePasswordRequest("12312")
        every { ctxMock.status(200) } returns ctxMock
        every { ctxMock.json(ValidatePasswordResponse(true)) } returns ctxMock
        every { serviceMock.validateReturnMessages(any()) } returns errors

        PasswordValidationController(serviceMock).validateWithErrorResponse(ctxMock)

        verify { ctxMock.status(200) }
        verify { ctxMock.json(ValidatePasswordResponse(true)) }
        verify { serviceMock.validateReturnMessages(any()) }
    }

}
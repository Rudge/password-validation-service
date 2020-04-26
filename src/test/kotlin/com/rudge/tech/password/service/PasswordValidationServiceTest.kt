package com.rudge.tech.password.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PasswordValidationServiceTest {

    @Test
    fun `given one password should validate this password with success and return true`() {
        val password = "AbTp9!foo"
        val success = PasswordValidationService().validate(password)
        Assertions.assertTrue(success)
    }

    @Test
    fun `given one password should validate this password with error and return false`() {
        val password = "AAAbbbCc"
        val success = PasswordValidationService().validate(password)
        Assertions.assertFalse(success)
    }

    @Test
    fun `given one blank password should validate this password with error and return false`() {
        val password = "        "
        val success = PasswordValidationService().validate(password)
        Assertions.assertFalse(success)
    }

}
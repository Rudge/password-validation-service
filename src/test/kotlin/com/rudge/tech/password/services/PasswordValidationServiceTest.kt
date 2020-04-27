package com.rudge.tech.password.services

import com.rudge.tech.password.service.PasswordValidationService
import com.rudge.tech.password.service.validations.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PasswordValidationServiceTest {

    private val rules = listOf(
            SizeNineCharsOrMorePasswordRule(),
            AtLeastOneNumberPasswordRule(),
            AtLeastOneLowerCasePasswordRule(),
            AtLeastOneCapitalLetterPasswordRule(),
            AtLeastOneSpecialCharPasswordRule()
    )

    @Test
    fun `given one password should validate this password with success and return true`() {
        val password = "AbTp9!foo"
        val success = PasswordValidationService(rules).validate(password)
        Assertions.assertTrue(success)
    }

    @Test
    fun `given one password should validate this password with error and return false`() {
        val password = "AAAbbbCc"
        val success = PasswordValidationService(rules).validate(password)
        Assertions.assertFalse(success)
    }

    @Test
    fun `given one blank password should validate this password with error and return false`() {
        val password = "        "
        val success = PasswordValidationService(rules).validate(password)
        Assertions.assertFalse(success)
    }

    @Test
    fun `given one password should validate this password with success and return empty error messages`() {
        val password = "AbTp9!foo"
        val errors = PasswordValidationService(rules).validateReturnMessages(password)
        Assertions.assertTrue(errors.isEmpty())
    }

    @Test
    fun `given one password should validate this password with error and return the error messages`() {
        val password = "AAAbbbCc"
        val errors = PasswordValidationService(rules).validateReturnMessages(password)
        Assertions.assertTrue(errors.containsAll(listOf(
                "Invalid size, must have more than 9 chars!",
                "Must have at least 1 number!",
                "Must have at least 1 special char!")
        ))
    }

    @Test
    fun `given one blank password should validate this password with error and return the error messages`() {
        val password = "        "
        val errors = PasswordValidationService(rules).validateReturnMessages(password)
        Assertions.assertTrue(errors.containsAll(listOf(
                "Invalid size, must have more than 9 chars!",
                "Must have at least 1 number!",
                "Must have at least 1 lower case char!",
                "Must have at least 1 capital letter char!")
        ))
    }

}
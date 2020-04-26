package com.rudge.tech.password.service.validations.service

import com.rudge.tech.password.service.validations.AtLeastOneCapitalLetterPasswordRule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AtLeastOneCapitalLetterPasswordRuleTest {

    @Test
    fun `given a string should validate it has one capital letter`() {
        Assertions.assertTrue(AtLeastOneCapitalLetterPasswordRule().test("A"))
    }

    @Test
    fun `given a string should validate it doesn't have one capital letter`() {
        Assertions.assertFalse(AtLeastOneCapitalLetterPasswordRule().test("a"))
    }
}
package com.rudge.tech.password.service.validations.service

import com.rudge.tech.password.service.validations.AtLeastOneSpecialCharPasswordRule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AtLeastOneSpecialCharPasswordRuleTest {

    @Test
    fun `given a string should validate it has special char`() {
        Assertions.assertTrue(AtLeastOneSpecialCharPasswordRule().test("!"))
    }

    @Test
    fun `given a string should validate it doesn't have special char`() {
        Assertions.assertFalse(AtLeastOneSpecialCharPasswordRule().test("a"))
    }
}
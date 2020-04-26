package com.rudge.tech.password.service.validations.service

import com.rudge.tech.password.service.validations.SizeNineCharsOrMorePasswordRule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SizeNineCharsOrMorePasswordRuleTest {

    @Test
    fun `given a string should validate it has 9 chars`() {
        Assertions.assertTrue(SizeNineCharsOrMorePasswordRule().test("123456789"))
    }

    @Test
    fun `given a string should validate it doesn't have 9 chars`() {
        Assertions.assertTrue(SizeNineCharsOrMorePasswordRule().test("12345678"))
    }
}
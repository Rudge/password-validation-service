package com.rudge.tech.password.services.validations

import com.rudge.tech.password.service.validations.AtLeastOneNumberPasswordRule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AtLeastOneNumberPasswordRuleTest {

    @Test
    fun `given a string should validate it has one number`() {
        Assertions.assertTrue(AtLeastOneNumberPasswordRule().test("1"))
    }

    @Test
    fun `given a string should validate it doesn't have one number`() {
        Assertions.assertFalse(AtLeastOneNumberPasswordRule().test("a"))
    }
}
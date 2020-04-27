package com.rudge.tech.password.services.validations

import com.rudge.tech.password.service.validations.AtLeastOneLowerCasePasswordRule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AtLeastOneLowerCasePasswordRuleTest {

    @Test
    fun `given a string should validate it has one lower case char`() {
        Assertions.assertTrue(AtLeastOneLowerCasePasswordRule().test("a"))
    }

    @Test
    fun `given a string should validate it doesn't have one lower case char`() {
        Assertions.assertFalse(AtLeastOneLowerCasePasswordRule().test("A"))
    }
}
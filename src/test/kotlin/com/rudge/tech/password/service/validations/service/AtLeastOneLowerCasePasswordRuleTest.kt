package com.rudge.tech.password.service.validations.service

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
        Assertions.assertTrue(AtLeastOneLowerCasePasswordRule().test("A"))
    }
}
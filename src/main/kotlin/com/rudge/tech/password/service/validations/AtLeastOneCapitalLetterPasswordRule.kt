package com.rudge.tech.password.service.validations

class AtLeastOneCapitalLetterPasswordRule(override val invalidMessage: String = "Must have at least 1 capital letter char!") : PasswordRule {
    override fun test(password: String): Boolean {
        TODO("Not yet implemented")
    }
}
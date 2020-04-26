package com.rudge.tech.password.service.validations

class AtLeastOneSpecialCharPasswordRule(override val invalidMessage: String = "Must have at least 1 special char!") : PasswordRule {
    override fun test(password: String): Boolean {
        TODO("Not yet implemented")
    }
}
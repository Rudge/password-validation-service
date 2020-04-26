package com.rudge.tech.password.service.validations

class AtLeastOneNumberPasswordRule(override val invalidMessage: String = "Must have at least 1 number!") : PasswordRule {
    override fun test(password: String): Boolean {
        TODO("Not yet implemented")
    }
}
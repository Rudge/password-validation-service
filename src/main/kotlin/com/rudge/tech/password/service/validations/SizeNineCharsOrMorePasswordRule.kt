package com.rudge.tech.password.service.validations

class SizeNineCharsOrMorePasswordRule(override val invalidMessage: String = "Invalid size, must have more than 9 chars!") : PasswordRule {
    override fun test(password: String): Boolean {
        TODO("Not yet implemented")
    }
}
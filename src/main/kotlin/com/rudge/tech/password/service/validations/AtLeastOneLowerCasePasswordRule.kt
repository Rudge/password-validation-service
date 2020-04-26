package com.rudge.tech.password.service.validations

class AtLeastOneLowerCasePasswordRule(override val invalidMessage: String = "Must have at least 1 lower case char!") : PasswordRule {
    override fun test(password: String) = "[a-z]".toRegex().containsMatchIn(password)
}
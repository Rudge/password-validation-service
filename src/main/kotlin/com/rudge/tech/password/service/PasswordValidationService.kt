package com.rudge.tech.password.service

import com.rudge.tech.password.service.validations.*

class PasswordValidationService {
    private val passwordRules: List<PasswordRule> = getRules()

    fun validate(password: String): Boolean {
        return passwordRules.all { it.test(password) }
    }

    private fun getRules() = listOf(
            SizeNineCharsOrMorePasswordRule(),
            AtLeastOneNumberPasswordRule(),
            AtLeastOneLowerCasePasswordRule(),
            AtLeastOneCapitalLetterPasswordRule(),
            AtLeastOneSpecialCharPasswordRule()
    )
}
package com.rudge.tech.password.service

import com.rudge.tech.password.service.validations.*

class PasswordValidationService {
    private val passwordRules: List<PasswordRule> = getRules()

    fun validate(password: String) =
            password.takeIf { it.isNotBlank() }
                    ?.let { passwordRules.all { it.test(password) } } ?: false

    fun validateReturnMessages(password: String) =
            password.takeIf { it.isNotBlank() }
                    ?.let { passwordRules.map { it.takeIf { it.test(password) }?.invalidMessage } }


    private fun getRules() = listOf(
            SizeNineCharsOrMorePasswordRule(),
            AtLeastOneNumberPasswordRule(),
            AtLeastOneLowerCasePasswordRule(),
            AtLeastOneCapitalLetterPasswordRule(),
            AtLeastOneSpecialCharPasswordRule()
    )
}
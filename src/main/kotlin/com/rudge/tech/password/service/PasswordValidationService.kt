package com.rudge.tech.password.service

import com.rudge.tech.password.service.validations.PasswordRule

class PasswordValidationService(private val passwordRules: List<PasswordRule>) {

    fun validate(password: String) =
            password.takeIf { it.isNotBlank() }
                    ?.let { passwordRules.all { it.test(password) } } ?: false

    fun validateReturnMessages(password: String) =
            password.let { passwordRules.map { it.takeIf { !it.test(password) }?.invalidMessage } }.filterNotNull()
}
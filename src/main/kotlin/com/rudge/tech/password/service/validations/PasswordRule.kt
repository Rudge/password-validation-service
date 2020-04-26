package com.rudge.tech.password.service.validations

interface PasswordRule {
    val invalidMessage: String
    fun test(password: String): Boolean
}
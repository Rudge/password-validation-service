package com.rudge.tech.password

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItems
import org.junit.jupiter.api.Test

class PasswordValidationClientTest {

    @Test
    fun `given one string, should call the endpoint password validate and return status code 200 with response password valid`() {
        Given {
            port(7000)
            body("""{"password":"AbTp9!foo"}""")
        } When {
            post("/password/validate")
        } Then {
            statusCode(200)
            body("valid", equalTo("true"))
        }
    }

    @Test
    fun `given one string, should call the endpoint password validate and return status code 400 with password not valid`() {
        Given {
            port(7000)
            body("""{"password":"AAAbbbCc"}""")
        } When {
            post("/password/validate")
        } Then {
            statusCode(200)
            body("valid", equalTo("false"))
        }
    }

    @Test
    fun `given one string, should call the endpoint password validate and return status code 400 with invalid definitions of password`() {
        Given {
            port(7000)
            body("""{"password":"AAAbbbCc"}""")
        } When {
            post("/password/validate")
        } Then {
            statusCode(200)
            body("errors.messages",
                    hasItems(
                            "Must have at least 1 number!",
                            "Must have at least 1 special char!",
                            "Invalid size, must have more than 9 chars!"
                    )
            )
        }
    }
}
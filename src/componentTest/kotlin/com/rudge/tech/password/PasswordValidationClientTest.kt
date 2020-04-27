package com.rudge.tech.password

import com.rudge.tech.password.config.AppConfig
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItems
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class PasswordValidationClientTest {
    companion object {
        private val app = AppConfig.setup()

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            app.start()
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            app.stop()
        }
    }

    @Test
    fun `given one string, should call the endpoint password validate and return status code 200 with response password valid`() {
        Given {
            port(app.port())
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body("""{"value":"AbTp9!foo"}""")
        } When {
            post("/password/validate")
        } Then {
            statusCode(200)
            body("valid", equalTo(true))
        }
    }

    @Test
    fun `given one string, should call the endpoint password validate and return status code 400 with password not valid`() {
        Given {
            port(app.port())
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
            body("""{"value":"AAAbbbCc"}""")
        } When {
            post("/password/validate")
        } Then {
            statusCode(200)
            body("valid", equalTo(false))
        }
    }

    @Test
    fun `given one string, should call the endpoint password validate and return status code 400 with invalid definitions of password`() {
        Given {
            port(app.port())
            contentType("application/vnd.password_details+json")
            accept(ContentType.JSON)
            body("""{"value":"AAAbbbCc"}""")
        } When {
            post("/password/validate")
        } Then {
            statusCode(400)
            body("messages",
                    hasItems(
                            "Must have at least 1 number!",
                            "Must have at least 1 special char!",
                            "Invalid size, must have more than 9 chars!"
                    )
            )
        }
    }
}
package ru.dezerom.features.auth.check_token

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureCheckToken() {
    routing {
        post("/check-token") {
            val token = call.receive<Token>()

            val controller = CheckTokenController()
            call.response.status(controller.checkToken(token))
        }
    }
}

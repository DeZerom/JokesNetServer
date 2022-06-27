package ru.dezerom.features.auth.login

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.dezerom.features.auth.Credentials

fun Application.configureLogin() {

    routing {
        post("/login") {
            val credentials = call.receive<Credentials>()

            val controller = LoginController()
            val token = controller.login(credentials)

            token?.let {
                call.respond(HttpStatusCode.OK, LoginResponse(token))
            } ?: run {
                call.response.status(HttpStatusCode.Forbidden)
            }
        }
    }
}

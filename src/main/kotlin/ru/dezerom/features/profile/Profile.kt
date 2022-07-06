package ru.dezerom.features.profile

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.dezerom.features.auth.check_token.Token

fun Application.configureProfile() {
    routing {
        post("/profile") {
            val token = call.receive<Token>()
            val controller = ProfileController()

            val profileInfo = try {
                controller.getProfileInfo(token)
            } catch (e: IllegalArgumentException) {
                call.response.status(HttpStatusCode.InternalServerError)
            }
            profileInfo?.let {
                call.respond(it)
            } ?: run {
                call.response.status(HttpStatusCode.Unauthorized)
            }
        }
    }
}
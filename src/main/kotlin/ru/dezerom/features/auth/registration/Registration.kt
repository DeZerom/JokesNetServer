package ru.dezerom.features.auth.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.dezerom.features.auth.Credentials

fun Application.configureRegistration() {

    routing {
        post("/registration") {
            val credentials = call.receive<Credentials>()
            val registrationController = RegistrationController()

            call.response.status(registrationController.registerNewUser(credentials))
        }
    }

}
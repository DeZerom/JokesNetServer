package ru.dezerom.features.auth.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.dezerom.features.auth.Credentials
import java.util.logging.Level
import java.util.logging.Logger

fun Application.configureRegistration() {

    routing {
        post("/registration") {
            val credentials = call.receive<Credentials>()
            val registrationController = RegistrationController()
            Logger.getAnonymousLogger().log(Level.INFO, "$credentials")

            val registrationResponse = RegistrationResponse(registrationController.registerNewUser(credentials))
            if (registrationResponse.status != RegistrationResponseStatus.OK)
                call.respond(HttpStatusCode.Forbidden, registrationResponse)
            else
                call.respond(HttpStatusCode.OK, registrationResponse)
        }
    }

}
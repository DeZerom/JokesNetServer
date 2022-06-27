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

            if (credentials.login == "admin" && credentials.pass == "admin") {
                call.respondText("ok", status = HttpStatusCode.OK)
            } else {
                call.respondText("not OK", status = HttpStatusCode.Unauthorized)
            }
        }
    }
}

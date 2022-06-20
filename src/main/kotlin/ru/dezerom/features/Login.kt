package ru.dezerom.features

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.dezerom.features.LoginRemote

fun Application.configureRouting() {

    routing {
        post("/login") {
            val credentials = call.receive<LoginRemote>()

            if (credentials.login == "admin" && credentials.pass == "admin") {
                call.respondText("ok", status = HttpStatusCode.OK)
            } else {
                call.respondText("not OK", status = HttpStatusCode.Unauthorized)
            }
        }
    }
}

package ru.dezerom.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting() {

    routing {
        post("/login") {
            val login = call.parameters["login"]
            val pass = call.parameters["pass"]

            if (login == "admin" && pass == "admin") {
                call.respondText("ok", status = HttpStatusCode.OK)
            } else {
                call.respondText("not OK", status = HttpStatusCode.Unauthorized)
            }
        }
    }
}

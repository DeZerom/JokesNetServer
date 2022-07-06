package ru.dezerom.features.jokes.add

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import ru.dezerom.utils.AUTH_HEADER

fun Application.configureJokeAdding() {
    routing {
        post("jokes/add") {
            val joke = call.receive<NewJoke>()
            val token = call.request.headers[AUTH_HEADER] ?: ""

            val controller = JokeAddingController()

            call.response.status(controller.addJoke(joke, token))
        }
    }
}
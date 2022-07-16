package ru.dezerom.features.jokes.add

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import ru.dezerom.utils.AUTH_HEADER

fun Application.configureJokeAdding() {
    routing {
        post("jokes/add") {
            val controller = JokeAddingController()
            controller.addJoke(call)
        }
    }
}
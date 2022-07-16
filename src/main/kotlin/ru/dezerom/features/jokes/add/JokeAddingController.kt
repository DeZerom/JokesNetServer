package ru.dezerom.features.jokes.add

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.dezerom.databse.auth.token.TokenModel
import ru.dezerom.databse.auth.user.UserModel
import ru.dezerom.databse.jokes.JokeDTO
import ru.dezerom.databse.jokes.JokeModel
import ru.dezerom.utils.AUTH_HEADER
import java.util.UUID
import ru.dezerom.features.jokes.Joke

class JokeAddingController {

    suspend fun addJoke(call: ApplicationCall) {
        val tokenString = call.request.headers[AUTH_HEADER] ?: ""
        val jokeText = call.receive<NewJoke>()
        val userLogin = TokenModel.selectToken(tokenString)?.let {
            UserModel.fetchUser(it.userLogin)?.login
        }

        if (userLogin == null) {
            call.response.status(HttpStatusCode.Unauthorized)
            return
        }

        val joke = JokeDTO(
            id = UUID.randomUUID().toString(),
            creator = userLogin,
            text = jokeText.text
        )

        JokeModel.insert(joke)
        val jokeResp = Joke(
            id = joke.id,
            creator = joke.creator,
            text = joke.text
        )

        call.respond(HttpStatusCode.OK, jokeResp)
    }

}

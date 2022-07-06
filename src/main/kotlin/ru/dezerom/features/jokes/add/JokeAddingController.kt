package ru.dezerom.features.jokes.add

import io.ktor.http.*
import ru.dezerom.databse.auth.token.TokenModel
import ru.dezerom.databse.auth.user.UserModel
import ru.dezerom.databse.jokes.JokeDTO
import ru.dezerom.databse.jokes.JokeModel
import java.util.UUID

class JokeAddingController {

    suspend fun addJoke(jokeText: NewJoke, tokenString: String): HttpStatusCode {
        val token = TokenModel.selectToken(tokenString) ?: return HttpStatusCode.Unauthorized
        val userLogin = UserModel.fetchUser(token.userLogin)?.login ?:
            throw IllegalArgumentException("User with login = ${token.userLogin} does not exist")

        val joke = JokeDTO(
            id = UUID.randomUUID().toString(),
            creator = userLogin,
            text = jokeText.text
        )

        JokeModel.insert(joke)
        return HttpStatusCode.OK
    }

}

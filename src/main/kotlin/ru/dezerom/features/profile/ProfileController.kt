package ru.dezerom.features.profile

import io.ktor.http.*
import ru.dezerom.databse.auth.token.TokenModel
import ru.dezerom.databse.auth.user.UserModel
import ru.dezerom.databse.jokes.JokeModel
import ru.dezerom.features.auth.check_token.Token

class ProfileController {

    /**
     * @return [ProfileInfo] or null if no such token
     */
    suspend fun getProfileInfo(token: Token): ProfileInfo? {
        val tokenDTO = TokenModel.selectToken(token.token) ?: return null
        val jokesAdded = JokeModel.countUsersJokes(tokenDTO.userLogin)

        return ProfileInfo(
            login = tokenDTO.userLogin,
            jokesAdded = jokesAdded
        )
    }

}
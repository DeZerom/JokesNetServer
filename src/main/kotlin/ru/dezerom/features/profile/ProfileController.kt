package ru.dezerom.features.profile

import ru.dezerom.databse.auth.token.TokenModel
import ru.dezerom.databse.jokes.JokeModel

class ProfileController {

    /**
     * @return [ProfileInfo] or null if no such token
     */
    suspend fun getProfileInfo(token: String): ProfileInfo? {
        val tokenDTO = TokenModel.selectToken(token) ?: return null
        val jokesAdded = JokeModel.countUsersJokes(tokenDTO.userLogin)

        return ProfileInfo(
            login = tokenDTO.userLogin,
            jokesAdded = jokesAdded
        )
    }

}
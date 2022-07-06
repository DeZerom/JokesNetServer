package ru.dezerom.features.profile

import io.ktor.http.*
import ru.dezerom.databse.auth.token.TokenModel
import ru.dezerom.databse.auth.user.UserModel
import ru.dezerom.features.auth.check_token.Token

class ProfileController {

    /**
     * @return [ProfileInfo] or null if no such token
     * @throws IllegalArgumentException if user with given token does not exist
     */
    suspend fun getProfileInfo(token: Token): ProfileInfo? {
        val tokenDTO = TokenModel.selectToken(token.token) ?: return null
        val user = UserModel.fetchUser(tokenDTO.userLogin) ?: throw IllegalArgumentException("no such user")

        return ProfileInfo(
            login = user.login
        )
    }

}
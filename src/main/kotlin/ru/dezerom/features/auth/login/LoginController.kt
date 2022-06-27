package ru.dezerom.features.auth.login

import ru.dezerom.databse.auth.token.TokenDTO
import ru.dezerom.databse.auth.token.TokenModel
import ru.dezerom.databse.auth.user.UserModel
import ru.dezerom.features.auth.Credentials
import ru.dezerom.utils.sha256
import java.util.UUID

class LoginController {

    suspend fun login(credentials: Credentials): String? {
        val user = UserModel.fetchUser(credentials.login) ?: return null

        if (user.password != credentials.pass.sha256(user.salt)) return null

        val token = TokenDTO(
            id = UUID.randomUUID().toString(),
            userLogin = user.login,
            token = (user.login + user.password + UUID.randomUUID().toString()).sha256()
        )
        TokenModel.insert(token)

        return token.token
    }

}
package ru.dezerom.features.auth.check_token

import io.ktor.http.*
import ru.dezerom.databse.auth.token.TokenModel

class CheckTokenController {

    suspend fun checkToken(token: Token): HttpStatusCode {
        return if (TokenModel.checkToken(token.token)) {
            HttpStatusCode.OK
        } else HttpStatusCode.Unauthorized
    }

}
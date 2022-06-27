package ru.dezerom.features.auth.registration

import io.ktor.util.*
import ru.dezerom.databse.auth.UserDTO
import ru.dezerom.databse.auth.UserModel
import ru.dezerom.features.auth.Credentials
import java.util.UUID

class RegistrationController {

    suspend fun registerNewUser(credentials: Credentials): RegistrationResponseStatus {
        val isUserExists = UserModel.fetchUser(credentials.login) != null

        if (isUserExists) return RegistrationResponseStatus.ACCOUNT_EXISTS

        val salt = UUID.randomUUID().toString().substring(0, 8)
        val sha = getDigestFunction("SHA256") { salt }
        val userDTO = UserDTO(
            login = credentials.login,
            password = sha.invoke(credentials.pass).decodeToString(),
            salt = salt
        )
        UserModel.insert(userDTO)

        return RegistrationResponseStatus.OK
    }

}
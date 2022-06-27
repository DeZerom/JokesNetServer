package ru.dezerom.features.auth.registration

import ru.dezerom.databse.auth.user.UserDTO
import ru.dezerom.databse.auth.user.UserModel
import ru.dezerom.features.auth.Credentials
import ru.dezerom.utils.sha256
import java.util.UUID

class RegistrationController {

    suspend fun registerNewUser(credentials: Credentials): RegistrationResponseStatus {
        val isUserExists = UserModel.fetchUser(credentials.login) != null

        if (isUserExists) return RegistrationResponseStatus.ACCOUNT_EXISTS

        val salt = UUID.randomUUID().toString().substring(0, 8)
        val userDTO = UserDTO(
            login = credentials.login,
            password = credentials.pass.sha256(salt),
            salt = salt
        )
        UserModel.insert(userDTO)
        return RegistrationResponseStatus.OK
    }
}
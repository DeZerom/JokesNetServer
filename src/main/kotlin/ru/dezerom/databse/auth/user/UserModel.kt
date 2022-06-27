package ru.dezerom.databse.auth.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel: Table(name = "users") {
    private val login = UserModel.varchar("login", 25)
    private val password = UserModel.varchar("password", 64)
    private val salt = UserModel.varchar("salt", 8)

    suspend fun insert(userDTO: UserDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[salt] = userDTO.salt
            }
        }
    }

    suspend fun fetchUser(login: String): UserDTO? {
        return newSuspendedTransaction(Dispatchers.IO) {
            val userModel = select {
                UserModel.login eq login
            }.singleOrNull() ?: return@newSuspendedTransaction null

            UserDTO(
                login = userModel[UserModel.login],
                password = userModel[password],
                salt = userModel[salt]
            )
        }
    }
}
package ru.dezerom.databse.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel: Table(name = "users") {
    private val login = UserModel.varchar("login", 25)
    private val password = UserModel.varchar("password", 64)
    private val salt = UserModel.varchar("salt", 8)

    fun insert(userDTO: UserDTO) {
        transaction {
            insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[salt] = userDTO.salt
            }
        }
    }

    suspend fun fetchUser(login: String): UserDTO? {
        return withContext(Dispatchers.IO) {
            val userModel = transaction {
                select {
                    UserModel.login eq login
                }.singleOrNull()
            } ?: return@withContext null

            UserDTO(
                login = userModel[UserModel.login],
                password = userModel[password],
                salt = userModel[salt]
            )
        }

    }
}
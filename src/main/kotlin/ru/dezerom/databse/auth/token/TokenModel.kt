package ru.dezerom.databse.auth.token

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object TokenModel: Table(name = "tokens") {
    private val id = varchar("id", 36)
    private val userLogin = varchar("user_login", 25)
    private val token = varchar("token", 64)

    suspend fun insert(tokenDTO: TokenDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[id] = tokenDTO.id
                it[userLogin] = tokenDTO.userLogin
                it[token] = tokenDTO.token
            }
        }
    }

    suspend fun checkToken(token: String): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            select {
                TokenModel.token eq token
            }.singleOrNull()?.let { true } ?: false
        }
    }
}
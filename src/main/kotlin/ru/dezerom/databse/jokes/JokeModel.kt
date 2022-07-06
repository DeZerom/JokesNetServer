package ru.dezerom.databse.jokes

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object JokeModel: Table(name = "jokes") {
    private val id = varchar("id", 36)
    private val creator = varchar("creator", 25)
    private val text = varchar("text", 500)

    suspend fun insert(joke: JokeDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[id] = joke.id
                it[creator] = joke.creator
                it[text] = joke.text
            }
        }
    }

    suspend fun countUsersJokes(login: String): Int {
        return newSuspendedTransaction(Dispatchers.IO) {
            select {
                creator eq login
            }.count().toInt()
        }
    }
}
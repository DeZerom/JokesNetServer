package ru.dezerom

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.Schema
import ru.dezerom.features.auth.check_token.configureCheckToken
import ru.dezerom.features.auth.login.configureLogin
import ru.dezerom.features.auth.registration.configureRegistration
import ru.dezerom.features.jokes.add.configureJokeAdding
import ru.dezerom.features.profile.configureProfile
import ru.dezerom.plugins.*

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        password = "D3mI|)Postgre", user = "postgres"
    )

    embeddedServer(Netty, port = 12345, host = "0.0.0.0") {
        configureLogin()
        configureRegistration()
        configureCheckToken()
        configureProfile()
        configureJokeAdding()
        configureSerialization()
    }.start(wait = true)
}

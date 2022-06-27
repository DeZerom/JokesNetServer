package ru.dezerom

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.Schema
import ru.dezerom.features.auth.login.configureLogin
import ru.dezerom.features.auth.registration.configureRegistration
import ru.dezerom.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        password = "D3mI|)Postgre", user = "postgres",
        databaseConfig = DatabaseConfig.invoke {
            defaultSchema = Schema("JokesNet")
        }
    )

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureLogin()
        configureRegistration()
        configureSerialization()
    }.start(wait = true)
}

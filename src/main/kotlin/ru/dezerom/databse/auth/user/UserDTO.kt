package ru.dezerom.databse.auth.user

data class UserDTO(
    val login: String,
    val password: String,
    val salt: String
)
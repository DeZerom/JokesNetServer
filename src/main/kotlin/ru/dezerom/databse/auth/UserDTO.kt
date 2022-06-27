package ru.dezerom.databse.auth

data class UserDTO(
    val login: String,
    val password: String,
    val salt: String
)
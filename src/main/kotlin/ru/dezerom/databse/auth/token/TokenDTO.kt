package ru.dezerom.databse.auth.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenDTO(
    val id: String,
    val userLogin: String,
    val token: String
)
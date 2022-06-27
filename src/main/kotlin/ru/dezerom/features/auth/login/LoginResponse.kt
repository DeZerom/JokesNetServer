package ru.dezerom.features.auth.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
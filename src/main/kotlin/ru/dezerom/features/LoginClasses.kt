package ru.dezerom.features

import kotlinx.serialization.Serializable

@Serializable
data class LoginRemote(
    val login: String,
    val pass: String
)

@Serializable
data class LoginResponseRemote(
    val token: String
)
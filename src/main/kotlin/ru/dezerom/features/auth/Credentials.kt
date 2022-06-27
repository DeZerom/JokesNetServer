package ru.dezerom.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val login: String,
    val pass: String
)

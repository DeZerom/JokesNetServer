package ru.dezerom.features.auth.check_token

import kotlinx.serialization.Serializable

@Serializable
class Token(
    val token: String
)
package ru.dezerom.features.auth.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val status: RegistrationResponseStatus
)

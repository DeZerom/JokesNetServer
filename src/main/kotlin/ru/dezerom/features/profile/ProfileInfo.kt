package ru.dezerom.features.profile

import kotlinx.serialization.Serializable

@Serializable
class ProfileInfo(
    val login: String,
    val jokesAdded: Int
)
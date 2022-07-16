package ru.dezerom.features.jokes

import kotlinx.serialization.Serializable

@Serializable
class Joke(
    val id: String,
    val creator: String,
    val text: String
)

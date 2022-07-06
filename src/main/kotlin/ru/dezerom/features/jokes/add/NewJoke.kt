package ru.dezerom.features.jokes.add

import kotlinx.serialization.Serializable

@Serializable
class NewJoke(
    val text: String
)
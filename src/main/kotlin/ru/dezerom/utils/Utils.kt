package ru.dezerom.utils

import java.security.MessageDigest

fun String.sha256(salt: String): String {
    val bytes = (this + salt).toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}
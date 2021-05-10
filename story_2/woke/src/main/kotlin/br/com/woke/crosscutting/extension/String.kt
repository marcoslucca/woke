package br.com.woke.crosscutting.extension

import arrow.core.invalid
import arrow.core.nel
import arrow.core.valid
import br.com.woke.crosscutting.domain.ValidationError
import java.math.BigInteger
import java.security.MessageDigest

fun String.toMD5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

fun String.validateBlank(message: String, field: String? = null) =
    when {
        this.isBlank() -> ValidationError.DoesNotContain(message).nel().invalid()
        else -> this.valid()
    }

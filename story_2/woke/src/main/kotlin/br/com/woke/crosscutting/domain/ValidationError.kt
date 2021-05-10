package br.com.woke.crosscutting.domain

sealed class ValidationError(val message: String) {

    data class DoesNotContain(val value: String) : ValidationError("Did not contain $value")
}

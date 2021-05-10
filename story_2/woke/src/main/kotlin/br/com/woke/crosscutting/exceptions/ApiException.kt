package br.com.woke.crosscutting.exceptions

data class ApiException(
    val code: String? = null,
    val message: String? = null
)

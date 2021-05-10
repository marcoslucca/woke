package br.com.woke.domain.exception

class AuthenticationException(override val message: String): RuntimeException(message)

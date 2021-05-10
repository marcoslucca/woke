package br.com.woke.crosscutting.exceptions

import br.com.woke.domain.exception.AuthenticationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionsAdvice {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun onException(exception: Exception): ApiException {
        logException(exception)

        return ApiException(
            INTERNAL_SERVER_ERROR.name,
            exception.localizedMessage
        )
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException::class)
    fun onException(exception: javax.naming.AuthenticationException): ApiException {
        logException(exception)

        return ApiException(
            UNAUTHORIZED.name,
            exception.localizedMessage
        )
    }

    private fun extractMessage(exception: Throwable) = exception.cause?.localizedMessage ?: exception.message ?: ""

    private fun logException(exception: Throwable) = logger.error(extractMessage(exception), exception)
}

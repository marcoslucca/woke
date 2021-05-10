package br.com.woke.usecase.authenticateuser

import arrow.core.Option
import br.com.woke.crosscutting.extension.toMD5
import br.com.woke.crosscutting.extension.toOption
import br.com.woke.domain.exception.AuthenticationException
import br.com.woke.domain.repository.UserRepository
import br.com.woke.usecase.authenticateuser.resources.UserDTO
import br.com.woke.usecase.authenticateuser.resources.UserResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthenticateUserService(private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun authenticate(userDTO: UserDTO): Option<UserResponse> {
        logger.info("trying to authenticate user ${userDTO.username}")

        return userRepository.findByUsername(userDTO.username).toOption()
            .map {
                if (!it.authenticate(userDTO.password.toMD5())) {
                    logger.error("wrong password")
                    throw AuthenticationException("The username or password is wrong!")
                }
                logger.info("the password is right")

                UserResponse.create(it)
            }
    }
}

package br.com.woke.usecase.authenticateuser

import br.com.woke.domain.exception.AuthenticationException
import br.com.woke.domain.repository.UserRepository
import br.com.woke.usecase.domain.UserObjectMother
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional

@ExtendWith(MockKExtension::class)
class AuthenticateUserServiceTest {

    @MockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var authenticateUserService: AuthenticateUserService

    @Test
    fun `given an authentication request should return an authenticated user`() {
        val userDTO = UserDTOObjectMother().build()
        val userFromDataBase = UserObjectMother().build()
        val userExpected = UserResponseObjectMother()
            .apply { id = userFromDataBase.id.toString() }
            .build()

        every { userRepository.findByUsername(userDTO.username) } returns Optional.of(userFromDataBase)

        authenticateUserService.authenticate(userDTO)
            .also { result ->
                result.map { userResult ->
                    userResult shouldBeEqualTo userExpected
                }
            }
    }

    @Test
    fun `given an authentication request should throws an AuthenticationException`() {
        val userDTO = UserDTOObjectMother().build()
        val userFromDataBase = UserObjectMother().apply { password = "wrong password" }.build()

        every { userRepository.findByUsername(userDTO.username) } returns Optional.of(userFromDataBase)

        assertThrows<AuthenticationException> { authenticateUserService.authenticate(userDTO) }
    }
}

package br.com.woke.usecase.authenticateuser

import br.com.woke.AbstractComponentTest
import br.com.woke.domain.repository.UserRepository
import br.com.woke.usecase.domain.UserObjectMother
import org.apache.http.HttpStatus
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import javax.transaction.Transactional

@Transactional
class AuthenticateUserControllerComponentTest : AbstractComponentTest() {

    @Autowired
    private lateinit var userRepository: UserRepository

    companion object {

        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:latest").apply {
            waitingFor(Wait.forLogMessage(".*ready to accept connections.*\\n", 1))
            print("Done!")
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.password", container::getPassword)
            registry.add("spring.datasource.username", container::getUsername)
        }
    }

    @Test
    fun `request to authenticate a user`() {
        val user = UserObjectMother().build()
        userRepository.deleteAll()
        userRepository.save(user)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(
                    EntityUtils.toString(
                        UrlEncodedFormEntity(
                            listOf(
                                BasicNameValuePair("username", "user1"),
                                BasicNameValuePair("password", "12345678")
                            )
                        )
                    )
                )
        )
            .andExpect {
                it.response.apply {
                    Assertions.assertEquals(HttpStatus.SC_OK, status)
                }
            }

        userRepository.delete(user)
    }

}

package br.com.woke.usecase.authenticateuser

import br.com.woke.usecase.authenticateuser.resources.UserDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class AuthenticateUserController(
    private val authenticateUserService: AuthenticateUserService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/login")
    fun authenticate(@ModelAttribute userDTO: UserDTO, model: Model): String {
        logger.info("authenticating user")

        return authenticateUserService.authenticate(userDTO)
            .fold(
                {
                    logger.info("no user found!")
                    "error"
                },
                {
                    logger.info("the user ${userDTO.username} is authenticated")

                    model.addAttribute("user", it)
                    "welcome"
                }
            )
    }
}

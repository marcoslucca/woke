package br.com.woke.usecase.gotologin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GoToController {

    @GetMapping("/")
    fun goToLogin(): String {
        return "login"
    }
}

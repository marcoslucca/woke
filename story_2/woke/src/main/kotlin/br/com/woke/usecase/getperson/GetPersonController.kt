package br.com.woke.usecase.getperson

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView

@Controller
class GetPersonController(
    private val getPersonService: GetPersonService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/person")
    fun authenticate(@RequestParam("username") username: String, modelMap: ModelMap): RedirectView {
        logger.info("finding person")

        return getPersonService.findBy(username)
            ?.let {
                modelMap.addAttribute("person", it)

                RedirectView()
                    .apply {
                        setContextRelative(true)
                        url = "/welcome"
                    }
            }
            ?: RedirectView()
                .apply {
                    setContextRelative(true)
                    url = "/person-not-found"
                }
    }
}

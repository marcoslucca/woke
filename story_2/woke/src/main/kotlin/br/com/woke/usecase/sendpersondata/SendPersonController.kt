package br.com.woke.usecase.sendpersondata

import br.com.woke.usecase.sendpersondata.resources.PersonDTO
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class SendPersonController(
    private val sendPersonDataService: SendPersonDataService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/send-person")
    fun send(@RequestBody person: PersonDTO): ResponseEntity<Void> {
        logger.info("sending person data")

        sendPersonDataService.send(person)

        return ResponseEntity.ok().build()
    }
}

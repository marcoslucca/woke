package br.com.woke.usecase.sendpersondata

import br.com.woke.infrastructure.ExternalClient
import br.com.woke.infrastructure.ExternalClientRequest
import br.com.woke.usecase.sendpersondata.resources.PersonDTO
import org.springframework.stereotype.Service

@Service
class SendPersonDataService(private val externalClient: ExternalClient) {

    fun send(personDTO: PersonDTO) {
        externalClient.send(ExternalClientRequest.create(personDTO))
    }
}

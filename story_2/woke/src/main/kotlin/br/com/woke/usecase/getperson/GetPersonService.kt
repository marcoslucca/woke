package br.com.woke.usecase.getperson

import br.com.woke.domain.repository.PersonRepository
import br.com.woke.usecase.sendpersondata.resources.PersonDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GetPersonService(private val personRepository: PersonRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findBy(username: String): PersonDTO? {
        logger.info("trying to find a person by username $username")

        val findByUsersUsername = personRepository.findByUsersUsername(username)
        return findByUsersUsername
            ?.let { PersonDTO.create(it) }
    }
}

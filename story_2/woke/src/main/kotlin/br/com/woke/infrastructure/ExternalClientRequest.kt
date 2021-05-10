package br.com.woke.infrastructure

import br.com.woke.usecase.sendpersondata.resources.PersonDTO
import com.fasterxml.jackson.annotation.JsonProperty

data class ExternalClientRequest(
    @get:JsonProperty("person_id")
    val personId: String,
    @get:JsonProperty("full_name")
    val fullName: String,
    val email: String,
    @get:JsonProperty("birth_date")
    val birthDate: String
) {

    companion object {
        fun create(personDTO: PersonDTO) = ExternalClientRequest(
            personId = personDTO.id.toString(),
            fullName = personDTO.fullName,
            email = personDTO.email,
            birthDate = personDTO.birthDate
        )
    }
}

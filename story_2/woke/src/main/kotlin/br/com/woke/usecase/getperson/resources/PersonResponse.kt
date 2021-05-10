package br.com.woke.usecase.getperson.resources

import br.com.woke.domain.entity.Person
import br.com.woke.usecase.sendpersondata.resources.PersonDTO

data class PersonResponse(
    val id: String,
    val fullName: String,
    val email: String,
    val birthDate: String
) {

    companion object {
        fun create(person: Person) = PersonDTO(
            id = person.id.toString(),
            fullName = person.fullName,
            email = person.email,
            birthDate = person.birthDate
        )
    }
}

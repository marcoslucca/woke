package br.com.woke.usecase.sendpersondata.resources

data class PersonDTO(
    val id: String,
    val fullName: String,
    val email: String,
    val birthDate: String
)

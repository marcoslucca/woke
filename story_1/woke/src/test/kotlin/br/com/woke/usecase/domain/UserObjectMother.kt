package br.com.woke.usecase.domain

import br.com.woke.domain.entity.User
import java.util.UUID

class UserObjectMother {

    var id: UUID = UUID.randomUUID()
    var username = "user1"
    var password = "25d55ad283aa400af464c76d713c07ad"

    fun build() = User(id, username, password)
}

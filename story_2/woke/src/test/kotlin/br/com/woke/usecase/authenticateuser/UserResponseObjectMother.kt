package br.com.woke.usecase.authenticateuser

import br.com.woke.usecase.authenticateuser.resources.UserResponse
import java.util.UUID

class UserResponseObjectMother {

    var id = UUID.randomUUID().toString()
    var username = "user1"

    fun build() = UserResponse(id, username)
}

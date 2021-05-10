package br.com.woke.usecase.authenticateuser

import br.com.woke.usecase.authenticateuser.resources.UserDTO

class UserDTOObjectMother {

    var username = "user1"
    var password = "12345678"

    fun build() = UserDTO(username, password)
}

package br.com.woke.usecase.authenticateuser.resources

import br.com.woke.domain.entity.User

data class UserResponse(
    val id: String,
    val username: String
) {

    companion object {
        fun create(user: User) = UserResponse(
            id = user.id.toString(),
            username = user.username
        )
    }
}

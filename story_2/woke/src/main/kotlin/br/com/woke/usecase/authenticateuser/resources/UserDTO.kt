package br.com.woke.usecase.authenticateuser.resources

import javax.validation.constraints.NotBlank

data class UserDTO(
    @NotBlank(message = "Username is mandatory")
    val username: String,
    @NotBlank(message = "Password is mandatory")
    val password: String
)

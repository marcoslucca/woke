package br.com.woke.domain.entity

import arrow.core.Nel
import arrow.core.Validated
import arrow.core.extensions.nonemptylist.semigroup.semigroup
import arrow.core.extensions.validated.applicative.applicative
import arrow.core.fix
import br.com.woke.crosscutting.domain.AbstractEntity
import br.com.woke.crosscutting.domain.ValidationError
import br.com.woke.crosscutting.extension.validateBlank
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    var person: Person?,

    @Version
    val version: Long = 0
) : AbstractEntity<User>() {

    fun authenticate(passwordConfirm: String) = passwordConfirm == password

    override fun validate(): Validated<Nel<ValidationError>, User> =
        Validated.applicative<Nel<ValidationError>>(Nel.semigroup())
            .mapN(
                username.validateBlank("Username can not be empty"),
                password.validateBlank("Password can not be empty")
            ) {
                this.copy()
            }.fix()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', version=$version)"
    }
}

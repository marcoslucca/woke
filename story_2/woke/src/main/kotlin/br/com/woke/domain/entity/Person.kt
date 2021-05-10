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
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(name = "person")
data class Person(
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val fullName: String,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val birthDate: String,

    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var users: Set<User>?,

    @Version
    val version: Long = 0
) : AbstractEntity<Person>() {

    override fun validate(): Validated<Nel<ValidationError>, Person> =
        Validated.applicative<Nel<ValidationError>>(Nel.semigroup())
            .mapN(
                fullName.validateBlank("Name can not be empty"),
                phone.validateBlank("Phone can not be empty"),
                birthDate.validateBlank("Birth Date can not be empty")
            ) {
                this.copy()
            }.fix()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false
        if (fullName != other.fullName) return false
        if (phone != other.phone) return false
        if (email != other.email) return false
        if (birthDate != other.birthDate) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + version.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(id=$id, fullName='$fullName', phone='$phone', email='$email', birthDate='$birthDate', version=$version)"
    }
}

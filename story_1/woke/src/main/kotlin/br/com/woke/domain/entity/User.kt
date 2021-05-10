package br.com.woke.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
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

    @Version
    val version: Long = 0,

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
) {

    fun authenticate(passwordConfirm: String) = passwordConfirm == password
}

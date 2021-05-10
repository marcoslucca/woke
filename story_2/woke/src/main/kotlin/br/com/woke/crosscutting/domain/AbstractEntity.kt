package br.com.woke.crosscutting.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column

abstract class AbstractEntity<T> : ValidatorAware<T> {
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    var createdAt: LocalDateTime? = null

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
}

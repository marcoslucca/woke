package br.com.woke.domain.repository

import br.com.woke.domain.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonRepository : JpaRepository<Person, UUID> {

    @Query(
        """
        SELECT user.person FROM User user
        WHERE user.username = :username
        """
    )
    fun findByUsersUsername(username: String): Person?
}

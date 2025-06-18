package com.alanpugachev.fuzzy_remake.repository

import com.alanpugachev.fuzzy_remake.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUsername(username: String): User?

    fun existsByUsername(username: String): Boolean

    fun deleteUserByUsername(username: String)

    fun deleteUserById(id: Long)
}
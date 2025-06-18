package com.alanpugachev.fuzzy_remake.service

import com.alanpugachev.fuzzy_remake.dto.UserRegistrationDTO
import com.alanpugachev.fuzzy_remake.entity.User
import com.alanpugachev.fuzzy_remake.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun createUser(dto: UserRegistrationDTO): User {
        if (userRepository.existsByUsername(dto.username)) {
            throw RuntimeException("User with username \"${dto.username}\" already exists")
        }

        return userRepository.save<User>(
            User(
                username = dto.username,
                firstName = dto.firstName,
                secondName = dto.secondName
            )
        )
    }
}
package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.dto.UserRegistrationDTO
import com.alanpugachev.fuzzy_remake.entity.User
import com.alanpugachev.fuzzy_remake.repository.UserRepository
import com.alanpugachev.fuzzy_remake.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = ["http://localhost:3000"]) /* only for testing */
class AdminController(
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    @GetMapping("/allUsers")
    fun getAllUsers() = userRepository.findAll()

    @PostMapping("/addNewUser")
    fun addNewUser(@RequestBody userRegistrationDTO: UserRegistrationDTO): ResponseEntity<Any> {
        val user: User = try {
            userService.createUser(dto = userRegistrationDTO)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("${e.message}")
        }

        return ResponseEntity.ok(mapOf(
            "message" to "User registered successfully",
            "userId" to user.id
        ))
    }
}
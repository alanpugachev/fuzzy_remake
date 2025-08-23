package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.dto.UserDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController() {
    @PostMapping("/login")
    fun login(
        @RequestBody
        userDTO: UserDTO
    ): String {
        println("username = ${userDTO.username}")
        println("password = ${userDTO.password}")

        return "aboba"
    }
}
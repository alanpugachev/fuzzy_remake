package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/home")
class HomeController(
    private val userRepository: UserRepository
) {
    @GetMapping("/")
    fun getHomePage() = "user 1 = ${userRepository.findUserByUsername("admin")}"
}
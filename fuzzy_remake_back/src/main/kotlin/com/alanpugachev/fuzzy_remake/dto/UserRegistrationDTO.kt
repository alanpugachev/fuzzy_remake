package com.alanpugachev.fuzzy_remake.dto

import jakarta.validation.constraints.NotBlank

data class UserRegistrationDTO(
    @NotBlank
    val username: String,

    @NotBlank
    val firstName: String,

    @NotBlank
    val secondName: String
)

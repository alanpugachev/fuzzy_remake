package com.alanpugachev.fuzzy_remake.dto

import java.time.LocalDateTime

data class AnswersDTO(
    val id: Long,
    val userId: Long,
    val rawAnswers: UsersSurveyAnswersDTO,
    val createdAt: LocalDateTime
)
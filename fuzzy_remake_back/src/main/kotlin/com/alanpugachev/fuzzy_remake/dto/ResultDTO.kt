package com.alanpugachev.fuzzy_remake.dto

import java.time.LocalDateTime

data class ResultDTO(
    val id: Long,
    val userId: Long,
    val rawResult: SurveyResult,
    val createdAt: LocalDateTime
)
package com.alanpugachev.fuzzy_remake.dto

data class SurveyAnswer(
    val questionId: String,
    val value: String
)

data class SurveyResponse(
    val answers: List<SurveyAnswer>
)
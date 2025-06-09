package com.alanpugachev.fuzzy_remake.dto

data class SurveyAnswer(
    val questionId: String,
    val value: String
)

data class SurveyResult(
    val answers: List<SurveyAnswer>
)
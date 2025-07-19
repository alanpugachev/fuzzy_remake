package com.alanpugachev.fuzzy_remake.repository

import com.alanpugachev.fuzzy_remake.entity.SurveyAnswers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswersRepository : JpaRepository<SurveyAnswers, Long> {
    fun save(surveyAnswers: SurveyAnswers): SurveyAnswers
}
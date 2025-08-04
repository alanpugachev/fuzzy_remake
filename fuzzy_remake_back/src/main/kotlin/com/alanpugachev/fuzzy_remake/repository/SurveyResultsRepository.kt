package com.alanpugachev.fuzzy_remake.repository

import com.alanpugachev.fuzzy_remake.entity.SurveyResults
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyResultsRepository : JpaRepository<SurveyResults, Long> {
    fun save(surveyResults: SurveyResults): SurveyResults
}
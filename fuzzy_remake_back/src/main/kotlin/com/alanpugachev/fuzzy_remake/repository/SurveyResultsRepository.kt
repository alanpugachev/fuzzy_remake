package com.alanpugachev.fuzzy_remake.repository

import com.alanpugachev.fuzzy_remake.entity.SurveyResults
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SurveyResultsRepository : JpaRepository<SurveyResults, Long> {
    fun save(surveyResults: SurveyResults): SurveyResults

    fun getByUserId(userId: Long): SurveyResults

    @Query(
        value = """
            SELECT sr FROM SurveyResults as sr
            WHERE sr.userId = :userId
            ORDER BY sr.createdAt DESC
            LIMIT 1
        """
    )
    fun getLastByUserId(userId: Long): SurveyResults
}
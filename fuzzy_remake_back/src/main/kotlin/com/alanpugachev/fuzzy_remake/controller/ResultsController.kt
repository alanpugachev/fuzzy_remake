package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.entity.SurveyResults
import com.alanpugachev.fuzzy_remake.repository.SurveyResultsRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = ["http://localhost:3000"]) /* only for testing */
class ResultsController(
    private val surveyResultsRepository: SurveyResultsRepository
) {
    /* todo change ResponseEntity generic */
    @GetMapping("/get-results")
    fun getResults(): ResponseEntity<SurveyResults?> {
        try {
            val rawSurveyResult: SurveyResults = surveyResultsRepository.getLastByUserId(1L)

            return ResponseEntity.ok(rawSurveyResult)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(null)
        }
    }
}
package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.dto.SurveyResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = ["http://localhost:3000"]) /* only for testing */
class SurveyController {
    @PostMapping("/submit-survey")
    fun handleSurveyFormSubmission(
        @RequestBody surveyResponse: SurveyResponse
    ): ResponseEntity<String> {
        try {
            println("Received answers: ${surveyResponse.answers}")

            return ResponseEntity.ok("Success")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("${e.message}")
        }
    }
}
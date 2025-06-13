package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.dto.ResultDTO
import com.alanpugachev.fuzzy_remake.dto.SurveyResult
import com.alanpugachev.fuzzy_remake.entity.Result
import com.alanpugachev.fuzzy_remake.producer.SurveyResultProducer
import com.alanpugachev.fuzzy_remake.repository.ResultRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = ["http://localhost:3000"]) /* only for testing */
class SurveyController(
    private val resultRepository: ResultRepository,
    private val surveyResultProducer: SurveyResultProducer
) {
    @PostMapping("/submit-survey")
    fun handleSurveyFormSubmission(
        @RequestBody surveyResult: SurveyResult
    ): ResponseEntity<String> {
        try {
            resultRepository.save(
                Result(
                    result = surveyResult,
                    createdAt = LocalDateTime.now()
                )
            )
                .also {
                    surveyResultProducer
                        .sendResultDtoMessage(
                            ResultDTO(
                                id = it.id ?: throw RuntimeException("Result id is missing"),
                                userId = 1,
                                rawResult = it.result,
                                createdAt = LocalDateTime.now()
                            )
                        )
                }

            return ResponseEntity.ok("Result was saved")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("${e.message}")
        }
    }
}
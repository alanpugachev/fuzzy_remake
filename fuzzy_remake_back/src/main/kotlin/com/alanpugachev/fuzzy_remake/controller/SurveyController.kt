package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.dto.AnswersDTO
import com.alanpugachev.fuzzy_remake.dto.UsersSurveyAnswersDTO
import com.alanpugachev.fuzzy_remake.entity.SurveyAnswers
import com.alanpugachev.fuzzy_remake.producer.SurveyResultProducer
import com.alanpugachev.fuzzy_remake.repository.AnswersRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = ["http://localhost:3000"]) /* only for testing */
class SurveyController(
    private val answersRepository: AnswersRepository,
    private val surveyResultProducer: SurveyResultProducer
) {
    @PostMapping("/submit-survey")
    fun handleSurveyFormSubmission(
        @RequestBody usersSurveyAnswersDTO: UsersSurveyAnswersDTO
    ): ResponseEntity<String> {
        try {
            answersRepository.save(
                SurveyAnswers(
                    userId = 1, /* todo */
                    answers = usersSurveyAnswersDTO,
                    createdAt = LocalDateTime.now()
                )
            )
                .also {
                    surveyResultProducer
                        .sendResultDtoMessage(
                            AnswersDTO(
                                id = it.id ?: throw RuntimeException("Result id not found"),
                                userId = 1,
                                rawAnswers = it.answers,
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
package com.alanpugachev.fuzzy_remake.consumer

import com.alanpugachev.fuzzy_remake.config.ASYNC_GROUP
import com.alanpugachev.fuzzy_remake.config.RESULTS_TOPIC
import com.alanpugachev.fuzzy_remake.dto.SurveyResultsDTO
import com.alanpugachev.fuzzy_remake.entity.SurveyResults
import com.alanpugachev.fuzzy_remake.repository.SurveyResultsRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SurveyProcessedResultConsumer(
    private val objectMapper: ObjectMapper,
    private val surveyResultsRepository: SurveyResultsRepository
) {
    @KafkaListener(topics = [RESULTS_TOPIC], groupId = ASYNC_GROUP)
    fun processMessage(jsonMessage: String) {
        try {
            val dto: SurveyResultsDTO = objectMapper.readValue(jsonMessage, SurveyResultsDTO::class.java)

            surveyResultsRepository
                .save(
                    SurveyResults(
                        userId = dto.userId,
                        rawResults = dto,
                        createdAt = LocalDateTime.now()
                    )
                )
        } catch (e: Exception) {
            println("Failed to parse survey result: ${e.message}")
        }
    }
}
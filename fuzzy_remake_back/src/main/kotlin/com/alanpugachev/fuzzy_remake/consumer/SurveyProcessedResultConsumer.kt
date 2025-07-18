package com.alanpugachev.fuzzy_remake.consumer

import com.alanpugachev.fuzzy_remake.config.ASYNC_GROUP
import com.alanpugachev.fuzzy_remake.config.RESULTS_TOPIC
import com.alanpugachev.fuzzy_remake.dto.SurveyResult
import com.alanpugachev.fuzzy_remake.entity.Result
import com.alanpugachev.fuzzy_remake.repository.ResultRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SurveyProcessedResultConsumer(
    private val resultRepository: ResultRepository,
    private val objectMapper: ObjectMapper
) {
    @KafkaListener(topics = [RESULTS_TOPIC], groupId = ASYNC_GROUP)
    fun processMessage(jsonMessage: String) {
        try {
            val surveyResult = objectMapper.readValue<SurveyResult>(jsonMessage)

            resultRepository.save(
                Result(
                    result = surveyResult,
                    createdAt = LocalDateTime.now()
                )
            )
        } catch (e: Exception) {
            println("Failed to parse survey result: ${e.message}")
        }
    }
}
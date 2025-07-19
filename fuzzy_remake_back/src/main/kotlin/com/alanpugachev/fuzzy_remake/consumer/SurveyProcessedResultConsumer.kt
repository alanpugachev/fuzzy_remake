package com.alanpugachev.fuzzy_remake.consumer

import com.alanpugachev.fuzzy_remake.config.ASYNC_GROUP
import com.alanpugachev.fuzzy_remake.config.RESULTS_TOPIC
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class SurveyProcessedResultConsumer(
    private val objectMapper: ObjectMapper
) {
    @KafkaListener(topics = [RESULTS_TOPIC], groupId = ASYNC_GROUP)
    fun processMessage(jsonMessage: String) {
        try {
            println(jsonMessage)
        } catch (e: Exception) {
            println("Failed to parse survey result: ${e.message}")
        }
    }
}
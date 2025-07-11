package com.alanpugachev.fuzzy_remake.producer

import com.alanpugachev.fuzzy_remake.config.RESULTS_TOPIC
import com.alanpugachev.fuzzy_remake.dto.ResultDTO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class SurveyResultProducer(
    private val kafkaTemplate: KafkaTemplate<String, ResultDTO>
) {
    fun sendResultDtoMessage(dto: ResultDTO) {
        kafkaTemplate.send(RESULTS_TOPIC, dto)
    }
}
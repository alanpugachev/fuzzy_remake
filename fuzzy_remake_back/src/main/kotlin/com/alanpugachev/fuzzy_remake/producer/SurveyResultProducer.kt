package com.alanpugachev.fuzzy_remake.producer

import com.alanpugachev.fuzzy_remake.config.KRAFT_TOPIC_NAME
import com.alanpugachev.fuzzy_remake.dto.AnswersDTO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class SurveyResultProducer(
    private val kafkaTemplate: KafkaTemplate<String, AnswersDTO>
) {
    fun sendResultDtoMessage(dto: AnswersDTO) {
        kafkaTemplate.send(KRAFT_TOPIC_NAME, dto)
    }
}
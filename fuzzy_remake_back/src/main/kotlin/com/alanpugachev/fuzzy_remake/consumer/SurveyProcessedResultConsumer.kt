package com.alanpugachev.fuzzy_remake.consumer

import com.alanpugachev.fuzzy_remake.config.ASYNC_GROUP
import com.alanpugachev.fuzzy_remake.config.RESULTS_TOPIC
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class SurveyProcessedResultConsumer {

    @KafkaListener(topics = [RESULTS_TOPIC], groupId = ASYNC_GROUP)
    fun processMessage(message: String?) {
        println(message)
    }
}
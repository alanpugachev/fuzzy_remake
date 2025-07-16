package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.consumer.SurveyProcessedResultConsumer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/results")
class ResultsController(
    private val surveyProcessedResultConsumer: SurveyProcessedResultConsumer
) {
    @GetMapping("/get-results")
    fun getResults(): ResponseEntity<String> {
        return ResponseEntity.ok("*--received results--*")
    }
}
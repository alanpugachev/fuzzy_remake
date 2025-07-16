package com.alanpugachev.fuzzy_remake.controller

import com.alanpugachev.fuzzy_remake.consumer.SurveyProcessedResultConsumer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = ["http://localhost:3000"]) /* only for testing */
class ResultsController(
    private val surveyProcessedResultConsumer: SurveyProcessedResultConsumer
) {
    @GetMapping("/get-results")
    fun getResults(): ResponseEntity<String> {
        return ResponseEntity.ok("*--received results--*")
    }
}
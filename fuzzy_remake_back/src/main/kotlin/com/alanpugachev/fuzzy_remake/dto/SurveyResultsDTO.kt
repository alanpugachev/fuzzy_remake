package com.alanpugachev.fuzzy_remake.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ScaleScores(
    @JsonProperty("low") val low: Double,
    @JsonProperty("mid") val mid: Double,
    @JsonProperty("elevated") val elevated: Double,
    @JsonProperty("high") val high: Double,
    @JsonProperty("very_high") val veryHigh: Double
)

data class SurveyResultsDTO(
    @JsonProperty("user_id") val userId: Long,
    @JsonProperty("hysteria") val hysteria: ScaleScores,
    @JsonProperty("hypochondria") val hypochondria: ScaleScores,
    @JsonProperty("depression") val depression: ScaleScores,
    @JsonProperty("psychopathy") val psychopathy: ScaleScores,
    @JsonProperty("paranoia") val paranoia: ScaleScores,
    @JsonProperty("psychasthenia") val psychasthenia: ScaleScores,
    @JsonProperty("schizophrenia") val schizophrenia: ScaleScores,
    @JsonProperty("hypomania") val hypomania: ScaleScores,
    @JsonProperty("introversion") val introversion: ScaleScores,
    @JsonProperty("processed_at") val processedAt: LocalDateTime,
    @JsonProperty("version") val version: String
)
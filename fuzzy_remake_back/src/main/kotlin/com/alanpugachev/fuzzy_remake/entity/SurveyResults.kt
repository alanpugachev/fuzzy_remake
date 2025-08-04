package com.alanpugachev.fuzzy_remake.entity

import com.alanpugachev.fuzzy_remake.dto.SurveyResultsDTO
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime

@Entity
@Table(name = "survey_results")
class SurveyResults(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_results")
    val rawResults: SurveyResultsDTO,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime
)
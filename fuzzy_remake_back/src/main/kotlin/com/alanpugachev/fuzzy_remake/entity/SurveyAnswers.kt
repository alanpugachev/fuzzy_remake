package com.alanpugachev.fuzzy_remake.entity

import com.alanpugachev.fuzzy_remake.dto.UsersSurveyAnswersDTO
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime

@Entity
@Table(name = "survey_answers")
class SurveyAnswers(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_result")
    val answers: UsersSurveyAnswersDTO,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime
)